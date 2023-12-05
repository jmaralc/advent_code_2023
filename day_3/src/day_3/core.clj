(ns day-3.core
  (:gen-class))

(def special-characters ["#" "+" "$" "*"])

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

(defn detect-engine-characters
  [input]
  (let [indexed-lines (doall (index-engine-lines input))]
    (into [] (reduce (fn[col cur] (concat col cur)) (keep find-character-inline indexed-lines)))
    )
  )

;(defn detect-numbers-in-line
;  [line]
;  (map
;    (fn[x] (Integer/parseInt x))
;    (filter
;      (fn [string] (> (count string) 1))
;      (let [substrings       (map
;                               (fn [string] (clojure.string/replace string "." ""))
;                               (re-seq #"[^\*\$\+\#]+" line)
;                               )
;            ]
;        (if (> (count substrings) 1) substrings [])
;        )
;
;      )
;    )
;  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
