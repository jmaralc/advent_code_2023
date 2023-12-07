(ns day-3.core
  (:gen-class))

(def special-characters ["#" "+" "$" "*"])
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
  [engine-character numbers]
  (filter (fn[number] (<= (chebyshev-distance engine-character number) 1)) numbers)
  )

(defn neighbours-positions?
  [point-1 point-2]
  (and (= (get point-1 0) (get point-2 0)) (<= (abs (- (get point-1 1) (get point-2 1))) 1))
  )

(defn have-neighbours
  [collection next-position]
  (if (= (count collection) 0) [next-position]
                               (cond
                                 (neighbours-positions? (last collection) next-position) collection
                                 :else (concat collection [next-position])
                                 )
                               )
    )
(defn get-neighbours-positions
  [engine-characters engine-numbers]
  (into [] (reduce (fn[col cur] (concat col cur)) (map (fn[character] (get-number-neighbours character engine-numbers)) engine-characters)
  )))

(defn get-candidate-positions
  [input]
  (let [engine-characters (detect-engine-characters input)]
    (let [engine-numbers (detect-engine-numbers input)]
      (let [neighbour-positions (get-neighbours-positions engine-characters engine-numbers)]
        (reduce have-neighbours [] (sort neighbour-positions))
        )
      )))

(defn get-number-of-candidate
  [input-lines candidate]
  (str (get (get input-lines (get candidate 0)) (get candidate 1)))
  )

(defn my-left-search
  [y current-line numbers-to-the-left]
  (if (< y 0) numbers-to-the-left
              (when (>= y 0)
              (let [current-char (get current-line y)]
                (if (not (some #(= (str current-char) %) numbers))
                  numbers-to-the-left
              (recur (dec y) current-line (str current-char numbers-to-the-left ))))))
  )

(defn get-whole-numbers
  [input-lines candidate]
  (let [initial-number (get-number-of-candidate input-lines candidate)]
    (let [current-line (get input-lines (get candidate 0))]
      (let [left-numbers (my-left-search (- (get candidate 1) 1) current-line "")]
        [(Integer/parseInt (str left-numbers initial-number))]
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

(defn get-part-numbers
  [input]
  (get-candidate-numbers input)
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
