(ns day-6.core
  (:gen-class))


(defn combine-press-time-and-max-distance
  [press-times max-distances]
  (map (fn [index] [(nth press-times index)  (nth max-distances index)]) (range (count press-times)))
  )


(defn extract-press-time
  [input]
  (map (fn [x] (Integer/parseInt x)) (re-seq #"\d+" input))
  )

(defn extract-press-time-part2
  [input]
  [(Long/parseLong (reduce str (re-seq #"\d+" input)))]
  )
(defn extract-max-dist
  [input]
  (map (fn [x] (Integer/parseInt x)) (re-seq #"\d+" input))
  )

(defn extract-max-dist-part2
  [input]
  [(Long/parseLong (reduce str (re-seq #"\d+" input)))]
  )

(defn split-input
  [input]
  (clojure.string/split-lines input)
  )

(defn get-distance
  [press-time total-time]
  (* (- total-time press-time) press-time)
  )
(defn compute-ways-to-beat-record
  [[press-time max-distance]]
  (count (filter (fn [x] (> x max-distance)) (map (fn [pt] (get-distance pt press-time)) (range press-time))))
  )

(defn compute-multiply-of-races-posibilities
  [input]
  (let [[press-time-input max-distance-input] (split-input input)]
    (let [games (combine-press-time-and-max-distance (extract-press-time press-time-input) (extract-max-dist max-distance-input))]
      (reduce * (map compute-ways-to-beat-record games))
      )
    )
  )

(defn compute-multiply-of-races-posibilities-part2
  [input]
  (let [[press-time-input max-distance-input] (split-input input)]
    (let [games (combine-press-time-and-max-distance (extract-press-time-part2 press-time-input) (extract-max-dist-part2 max-distance-input))]
      (reduce * (map compute-ways-to-beat-record games))
      )
    )
  )