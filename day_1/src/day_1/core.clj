(ns day-1.core
  (:gen-class))

(defn get-numbers-from
  [line]
  (let [numerized-line (clojure.string/replace line #"two|nine" {"two" "2" "nine" "9"})]
    (let [digits (clojure.string/replace (clojure.string/lower-case numerized-line) #"[a-z]" "")]
      (Integer/parseInt (str (first digits) (last digits)))
      )
    )
)

(defn get-sum-of-lines
  [lines]
  (reduce + (map get-numbers-from lines))
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

(defn -main
  [& args]
  (println (str
             "Result of processing lines: "
             (process-file "./resources/calibration_document.txt")))
  )