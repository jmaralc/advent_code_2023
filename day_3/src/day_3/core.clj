(ns day-3.core
  (:gen-class))

(def special-characters ["#" "+" "$" "*" "/" "%" "=" "&" "-" "@"])
(def gear-characters ["*"])
(def numbers ["0" "1" "2" "3" "4" "5" "6" "7" "8" "9"])

(defn index-engine-lines
  [input]
  (map-indexed (fn [idx itm] [idx itm]) (clojure.string/split-lines input))
  )


(defn find-character-inline
  [[line-index line-content]]
  (let [found-indexes (keep-indexed (fn [index character] (if (some #(= (str character) %) special-characters) [line-index index])) line-content)]
    (if (>= (count found-indexes) 1) found-indexes nil)
    )
  )

(defn find-gear-inline
  [[line-index line-content]]
  (let [found-indexes (keep-indexed (fn [index character] (if (some #(= (str character) %) gear-characters) [line-index index])) line-content)]
    (if (>= (count found-indexes) 1) found-indexes nil)
    )
  )

(defn find-number-inline
  [[line-index line-content]]
  (let [found-indexes (keep-indexed (fn [index character] (if (some #(= (str character) %) numbers) [line-index index])) line-content)]
    (if (>= (count found-indexes) 1) found-indexes nil)
    )
  )

(defn detect-engine-characters
  [input]
  (let [indexed-lines (doall (index-engine-lines input))]
    (into [] (reduce (fn[col cur] (concat col cur)) (keep find-character-inline indexed-lines)))
    )
  )

(defn detect-gear-characters
  [input]
  (let [indexed-lines (doall (index-engine-lines input))]
    (into [] (reduce (fn[col cur] (concat col cur)) (keep find-gear-inline indexed-lines)))
    )
  )

(defn detect-engine-numbers
  [input]
  (let [indexed-lines (doall (index-engine-lines input))]
    (into [] (reduce (fn[col cur] (concat col cur)) (keep find-number-inline indexed-lines)))
    )
  )

(defn taxicab-distance
  [point-1 point-2]
  (+ (abs (- (get point-1 0) (get point-2 0))) (abs (- (get point-1 1) (get point-2 1))) )
  )

(defn chebyshev-distance
  [point-1 point-2]
  (max (abs (- (get point-1 0) (get point-2 0))) (abs (- (get point-1 1) (get point-2 1))) )
  )

(defn get-number-neighbours
  [engine-character numbers distance-tolerance]
  (filter (fn[number] (<= (chebyshev-distance engine-character number) distance-tolerance)) numbers)
  )

(defn neighbours-positions?
  [point-1 point-2]
  (and (= (get point-1 0) (get point-2 0)) (<= (abs (- (get point-1 1) (get point-2 1))) 1))
  )

(defn remove-neighbours
  [original-collection new-collection current]
  (if (> current (- (count original-collection) 1))
    (sort new-collection)
    (if (= current 0)
      (recur  original-collection (conj new-collection (first original-collection)) (inc current))
      (let [current-position (nth original-collection current) ]
        (if (neighbours-positions? current-position (nth original-collection (- current 1)))
          (recur  original-collection new-collection (inc current))
          (recur  original-collection (conj new-collection current-position)  (inc current))
          )
        )
      )
    )
  )


(defn get-neighbours-positions
  [engine-characters engine-numbers distance-tolerance]
  (into [] (reduce (fn[col cur] (concat col cur)) (map (fn[character] (get-number-neighbours character engine-numbers distance-tolerance)) engine-characters)
  )))

(defn get-gears-positions
  [engine-characters engine-numbers distance-tolerance]
  (let [cands (map (fn[candidates] (remove-neighbours candidates [] 0)) (map (fn[character] (get-number-neighbours character engine-numbers distance-tolerance)) engine-characters))]
    (filter #(= (count %) 2) cands)
    ))


(defn get-candidate-positions
  [input]
  (let [engine-characters (detect-engine-characters input)]
    (let [engine-numbers (detect-engine-numbers input)]
      (let [neighbour-positions (get-neighbours-positions engine-characters engine-numbers 1)]
        (remove-neighbours (sort neighbour-positions) [] 0)
        )
      )))



(defn get-number-of-candidate
  [input-lines candidate]
  (str (get (get input-lines (get candidate 0)) (get candidate 1)))
  )

(defn my-left-search
  [index current-line numbers-to-the-left]
  (if (< index 0) numbers-to-the-left
  (let [current-char (get current-line index)]
    (if (not (some #(= (str current-char) %) numbers))
      numbers-to-the-left
  (recur (dec index) current-line (str current-char numbers-to-the-left )))))
  )

(defn my-right-search
  [index current-line numbers-to-the-right]
  (if (> index (count current-line)) numbers-to-the-right
                  (let [current-char (get current-line index)]
                    (if (not (some #(= (str current-char) %) numbers))
                      numbers-to-the-right
                      (recur (inc index) current-line (str numbers-to-the-right current-char)))))
  )

(defn get-whole-numbers
  [input-lines candidate]
  (let [initial-number (get-number-of-candidate input-lines candidate)]
    (let [current-line (get input-lines (get candidate 0))]
      (let [left-numbers (my-left-search (- (get candidate 1) 1) current-line "")]
        (let [right-numbers (my-right-search  (+ (get candidate 1) 1) current-line "")]
          (Long/parseLong (str left-numbers initial-number right-numbers))
          )
        )
      )

    )
  )

(defn get-candidate-numbers
  [input]
  (let [input-lines (clojure.string/split-lines input)]
    (map (fn[candidate] (get-number-of-candidate input-lines candidate)) (get-candidate-positions input))
    )
  )


(defn get-gear-ratios-positions
  [input]
  (let [gear-characters (detect-gear-characters input)]
    (let [engine-numbers (detect-engine-numbers input)]
      (get-gears-positions gear-characters engine-numbers 1)
      )
    ))

(defn get-ratios-numbers
  [input]
  (let [input-lines (clojure.string/split-lines input)]
    (let [part-numbers-pairs (map #(map (fn[candidate] (get-whole-numbers input-lines candidate)) %) (get-gear-ratios-positions input))]
      (map (fn [x](reduce * x))  (map flatten part-numbers-pairs))
      )
    )
  )

(defn get-part-numbers
  [input]
  (let [input-lines (clojure.string/split-lines input)]
    (map (fn[candidate] (get-whole-numbers input-lines candidate)) (get-candidate-positions input))
    )
  )


(defn sum-part-numbers
  [input]
  (reduce + (get-part-numbers input))
  )

(defn sum-ratio-numbers
  [input]
  (reduce + (get-ratios-numbers input))
  )

(defn get-sum-of-part-numbers-of-file
  [file-path]
  (sum-part-numbers (slurp file-path))
  )

(defn get-sum-of-ratios-of-file
  [file-path]
  (sum-ratio-numbers (slurp file-path))
  )
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
