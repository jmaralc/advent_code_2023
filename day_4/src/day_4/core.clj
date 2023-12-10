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
(defn get-card-index
  [card]
  (let [card-head (first (clojure.string/split card #":")) ]
    (Integer/parseInt (clojure.string/replace (last (clojure.string/split card-head #"[ \t]+")) #":" ""))
    ))

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

(defn map-to-n
  [n]
  (zipmap (range 1 n) (repeat 1))
  )

(defn get-initial-carry
  [input]
  (zipmap (range 1 (+ (count input) 1)) (repeat 1))
  )

(defn update-carry-on-index
  [carry index original-card-index]
  (update carry index + (* 1 (get carry original-card-index)))
  )
(defn update-carry
  [index wins carry]
  (let [range-of-cards (range (+ index 1) (+ index wins 1))]
    (reduce (fn [col val] (update-carry-on-index col val index))  carry range-of-cards)
    )
  )
(defn get-card-carry
  [carry card]
  (let [index (get-card-index card)]
    (let [deck-size (last (sort (keys carry)) )]
      (if (> index deck-size) carry
                              (let [[wining-numbers my-numbers]  (get-numbers card) ]
                                (let [common-numbers (clojure.set/intersection wining-numbers my-numbers)]
                                  (let [wins (count common-numbers)]
                                    (if (> wins 0) (update-carry index wins carry) carry)
                                    )
                                  )
                                )
                              )
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

(defn compute-all-cards
  [input]
  (let [cards (split-game-in-cards input)]
    (reduce + (vals (reduce get-card-carry (get-initial-carry cards) cards)) )
    )
  )

(defn get-points-of-input-file
  [file-path]
  (compute-total-points (slurp file-path))
  )

(defn get-cards-of-input-file
  [file-path]
  (compute-all-cards (slurp file-path))
  )


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
