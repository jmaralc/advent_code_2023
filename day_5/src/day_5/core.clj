(ns day-5.core
  (:gen-class))

(def default-map (into (sorted-map) (zipmap (range 0 99) (range 0 99))))

(defn line-to-sequence
  [line]
  (doall  (map #(Long/parseLong %) (clojure.string/split line #" ")))
  )

(defn sequenced-list-to-map
  [sequenced-list]
  (let [[destination-start source-start range-length] sequenced-list]
    (zipmap (range source-start (+ source-start range-length)) (range destination-start (+ destination-start range-length)))
    ))

(defn map-definition-to-maps
  [map-definition]
  (let [lines (clojure.string/split-lines map-definition)]
    (let [sequences (map line-to-sequence lines)]
      (map sequenced-list-to-map sequences)
      )
    )
  )

(defn map-definition-to-sequences
  [map-definition]
  (let [lines (clojure.string/split-lines map-definition)]
    (map line-to-sequence lines)
  ))


(defn generate-map
  [map-definition]
  (reduce merge default-map (map-definition-to-maps map-definition))
  )

(defn get-seeds
  [input]
  (map (fn [x] (Long/parseLong x)) (clojure.string/split (clojure.string/replace (first (clojure.string/split-lines input)) #"seeds: " "") #" ") )
  )

(defn get-seed-ranges
  [input]
  (let [seeds-sequence (get-seeds input)]
    (map (fn [[start length]] (range start (+ start length)))  (partition 2 seeds-sequence))
    )
  )

(defn get-maps
  [input]
  (let [map-definitions (re-seq #"(?s)(?<=map:\n).*?(?=\n\n)|(?s)(?<=map:\n).*?(?=\z)" input)]
    (map generate-map map-definitions)
    )
  )
(defn get-maps-sequences
  [input]
  (let [map-definitions (re-seq #"(?s)(?<=map:\n).*?(?=\n\n)|(?s)(?<=map:\n).*?(?=\z)" input)]
    (map map-definition-to-sequences map-definitions)
    )
  )
(defn apply-maps-to-seed
  [seed maps]
  (reduce (fn [subject-to-map map] (get map subject-to-map)) seed maps)
  )

(defn apply-maps-to-seed
  [seed maps]
  (reduce (fn [subject-to-map map] (get map subject-to-map)) seed maps)
  )

(defn seed-in-sequence
  [seed sequence]
  (let [[_ source range] sequence]
    (and (<= source seed) (< seed (+ source range)))
    )
  )
(defn apply-map-sequence-to-seed
  [seed map-sequence]
  (let [applicable-sequence (into [] (filter (fn[sequence] (seed-in-sequence seed sequence)) map-sequence)) ]
    (if (empty? applicable-sequence) seed
                                     (let [[[destination source _] & _]  applicable-sequence]
                                       (+ destination (- seed source))
                                       )
                                       )

  ))

(defn apply-all-maps-sequences-to-seed
  [seed maps-sequences]
  (reduce (fn [subject-to-map map-sequence] (apply-map-sequence-to-seed subject-to-map map-sequence)) seed maps-sequences)
  )

(defn get-lowest-location
  [input]
  (let [seeds (get-seeds input)]
    (let [maps (get-maps input)]
      (reduce min (map (fn [seed] (apply-maps-to-seed seed maps)) seeds))
      )
    )
  )

(defn get-lowest-location2
  [input]
  (let [seeds (get-seeds input)]
    (let [maps-sequences (get-maps-sequences input)]
      (reduce min (map (fn [seed] (apply-all-maps-sequences-to-seed seed maps-sequences)) seeds))
      )
    )
  )

(defn compute-seed-range
  [maps-sequences seed-range]
  (println (str "Processing"))
  (let [values (pmap (fn [seed](apply-all-maps-sequences-to-seed seed maps-sequences)) seed-range)]
    (reduce min values)
    )
  )
(defn get-lowest-location3
  [input]
  (let [seed-ranges (get-seed-ranges input)]
    (let [maps-sequences (get-maps-sequences input)]
      (let [locations (pmap (partial compute-seed-range maps-sequences) seed-ranges)]
        (reduce min locations)
        )
      )
    )
  )