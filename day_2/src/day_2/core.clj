(ns day-2.core
  (:gen-class))

(def default-withdraw {"red" 0 "green" 0 "blue" 0})
(def max_red 12)
(def max_green 13)
(def max_blue 14)

(defn assign-color
  [collection color]
  (let [[number, color_name] (clojure.string/split (clojure.string/replace-first color #" " "") #" ")]
    (assoc collection color_name (Integer/parseInt number))
  )
)

(defn assign-max-color
  [collection color]
  (let [[number, color_name] (clojure.string/split (clojure.string/replace-first color #" " "") #" ")]
    (assoc collection color_name (max (Integer/parseInt number) (get collection color_name)))
    )
  )

(defn get-withdraw-minimum
  [collection withdraw]
  (reduce assign-max-color collection (clojure.string/split withdraw #","))
  )

(defn compute-game-power
  [game]
  (let [game_values (last (clojure.string/split game #":"))]
    (reduce * (vals (reduce get-withdraw-minimum default-withdraw (clojure.string/split game_values #";"))))
    )
  )


(defn is-withdraw-possible
  [withdraw]
  (let [withdraw_combination (reduce assign-color default-withdraw (clojure.string/split withdraw #","))]
    (and
          (<= (get withdraw_combination "red") max_red)
          (<= (get withdraw_combination "green") max_green)
          (<= (get withdraw_combination "blue") max_blue)
          )
    )
  )

(defn is_game_valid
  [game]
  (every? true? (map is-withdraw-possible (clojure.string/split game #";")))
  )

(defn get-game-id
  [detailed-game]
  (let [[game-id game] (clojure.string/split detailed-game #":")]
    (let [id (Integer/parseInt (clojure.string/replace game-id #"Game " ""))]
      (if (is_game_valid game) id 0)
      )
    )
  )

(defn get-sum-of-lines
  [lines]
  (reduce + (map get-game-id lines))
  )

(defn get-power-of-lines
  [lines]
  (reduce + (map compute-game-power lines))
  )

(defn process-file
  [file-path]
  (with-open [rdr (clojure.java.io/reader file-path)]
    (let [lines (line-seq rdr)]
      (println lines)
      (get-sum-of-lines lines)
      )
    )
  )


(defn get-powers-of-file
  [file-path]
  (with-open [rdr (clojure.java.io/reader file-path)]
    (let [lines (line-seq rdr)]
      (get-power-of-lines lines)
      )
    )
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

