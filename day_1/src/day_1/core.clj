(ns day-1.core
  (:gen-class))

(defn get-numbers-from
  [line]
  (let [numerized-line (
                         clojure.string/replace line
                                                #"one|two|three|four|five|six|seven|eight|nine"
                                                {"one" "1" "two" "2" "three" "3" "four" "4" "five" "5" "six" "6" "seven" "7" "eight" "8" "nine" "9"})]
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