(ns day-4.core
  (:require [clojure.math :as math]
            [clojure.set :as set])
  (:gen-class))


(defn remove-empty-strings
  [input]
  (filter (fn [x] (> (count x) 0)) (clojure.string/split (clojure.string/trim input) #" "))
  )

(defn parse-string-numbers-to-int
  [string]
  (Integer/parseInt string)
  )

(defn get-numbers
  [card]
  (let [card-body (last (clojure.string/split card #":")) ]
    (let [[wining-numbers my-numbers] (clojure.string/split card-body  #"\|") ]
      [(set (map parse-string-numbers-to-int (remove-empty-strings wining-numbers)))
       (set (map parse-string-numbers-to-int (remove-empty-strings my-numbers)))
       ]
      )
    )
  )

(defn get-card-points
  [card]
  (let [[wining-numbers my-numbers]  (get-numbers card) ]
    (let [common-numbers (clojure.set/intersection wining-numbers my-numbers)]
      (int (math/pow 2 (- (count common-numbers) 1) ))
      )
    )
  )

(defn split-game-in-cards
  [input]
  (clojure.string/split-lines input)
  )

(defn compute-total-points
  [input]
  (reduce + (map get-card-points (split-game-in-cards input)) )
  )

(defn get-points-of-input-file
  [file-path]
  (compute-total-points (slurp file-path))
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
