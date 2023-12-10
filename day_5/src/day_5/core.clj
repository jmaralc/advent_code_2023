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

(defn generate-map
  [map-definition]
  (reduce merge default-map (map-definition-to-maps map-definition))
  )

(defn get-seeds
  [input]
  (map (fn [x] (Long/parseLong x)) (clojure.string/split (clojure.string/replace (first (clojure.string/split-lines input)) #"seeds: " "") #" ") )
  )

(defn get-maps
  [input]
  (let [map-definitions (re-seq #"(?s)(?<=map:\n).*?(?=\n\n)|(?s)(?<=map:\n).*?(?=\z)" input)]
    (map generate-map map-definitions)
    )
  )

(defn apply-maps-to-seed
  [seed maps]
  (reduce (fn [subject-to-map map] (get map subject-to-map)) seed maps)
  )

(defn get-lowest-location
  [input]
  (let [seeds (get-seeds input)]
    (let [maps (get-maps input)]
      (reduce min (map (fn [seed] (apply-maps-to-seed seed maps)) seeds))
      )
    )
  )