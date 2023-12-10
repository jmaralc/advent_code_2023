(ns day-5.core-test
  (:require [clojure.test :refer :all]
            [day-5.core :refer :all]))

(def test-mapping-result {0  0
                          1  1
                          10 10
                          11 11
                          12 12
                          13 13
                          14 14
                          15 15
                          16 16
                          17 17
                          18 18
                          19 19
                          2  2
                          20 20
                          21 21
                          22 22
                          23 23
                          24 24
                          25 25
                          26 26
                          27 27
                          28 28
                          29 29
                          3  3
                          30 30
                          31 31
                          32 32
                          33 33
                          34 34
                          35 35
                          36 36
                          37 37
                          38 38
                          39 39
                          4  4
                          40 40
                          41 41
                          42 42
                          43 43
                          44 44
                          45 45
                          46 46
                          47 47
                          48 48
                          49 49
                          5  5
                          50 52
                          51 53
                          52 54
                          53 55
                          54 56
                          55 57
                          56 58
                          57 59
                          58 60
                          59 61
                          6  6
                          60 62
                          61 63
                          62 64
                          63 65
                          64 66
                          65 67
                          66 68
                          67 69
                          68 70
                          69 71
                          7  7
                          70 72
                          71 73
                          72 74
                          73 75
                          74 76
                          75 77
                          76 78
                          77 79
                          78 80
                          79 81
                          8  8
                          80 82
                          81 83
                          82 84
                          83 85
                          84 86
                          85 87
                          86 88
                          87 89
                          88 90
                          89 91
                          9  9
                          90 92
                          91 93
                          92 94
                          93 95
                          94 96
                          95 97
                          96 98
                          97 99
                          98 50
                          99 51})

(def test-map-ranges
  "50 98 2
52 50 48"
  )

(deftest test-line-to-list
  (testing "should return a list with similar values to the string"
    (is (= (line-to-sequence "50 98 2") [50 98 2]))))


(deftest test-sequenced-list-to-map
  (testing "should return a map with the positions that are going to be transformed"
    (is (= (sequenced-list-to-map [50 98 2]) {98 50, 99 51}))))

(deftest generate-map-test
  (testing "should create a map based on a given input"
    (is (= (generate-map test-map-ranges) (into (sorted-map) test-mapping-result) ))
    ))

(deftest get-seeds-test
  (testing "should return a list of seeds from an input"
    (is (= (get-seeds (slurp "./resources/test-input.txt")) [79 14 55 13]))
    ))

(deftest get-maps-test
  (testing "should return a list of seeds from an input"
    (is (= (count (get-maps (slurp "./resources/test-input.txt"))) 7 ))
    ))

(deftest apply-maps-to-seed-test
  (testing "should return location 82 for the first map"
    (is (= (apply-maps-to-seed 79 (get-maps (slurp "./resources/test-input.txt"))) 82))
    ))
(deftest apply-maps-to-seed-test-2
  (testing "should return location 43 for the first map"
    (is (= (apply-maps-to-seed 14 (get-maps (slurp "./resources/test-input.txt"))) 43))
    ))
(deftest apply-maps-to-seed-test-3
  (testing "should return location 86 for the first map"
    (is (= (apply-maps-to-seed 55 (get-maps (slurp "./resources/test-input.txt"))) 86))
    ))

(deftest apply-maps-to-seed-test-4
  (testing "should return location 35 for the first map"
    (is (= (apply-maps-to-seed 13 (get-maps (slurp "./resources/test-input.txt"))) 35))
    ))

(deftest get-lowest-location-from-test-input
  (testing "should return 35 when apply to test input"
    (is (= (get-lowest-location (slurp "./resources/test-input.txt")) 35))))



(deftest get-lowest-location-from-real-input
  (testing "should return 35 when apply to real input"
    (is (= (get-lowest-location (slurp "./resources/input.txt")) 35))))

(deftest map-definition-to-sequences-test
  (testing "should return the sequences belonging to a map definition"
    (is (= (map-definition-to-sequences test-map-ranges)  [[50 98 2] [52 50 48]]))))

(deftest apply-map-sequence-to-seed-test
  (testing "should return the value of the seed in the after applying the map"
    (is (= (apply-map-sequence-to-seed 79 [[50 98 2] [52 50 48]]) 81))))

(deftest apply-map-sequence-to-seed-test
  (testing "should return the value of the seed in the after applying the map"
    (is (= (apply-map-sequence-to-seed 1 [[50 98 2] [52 50 48]]) 1))))


(deftest get-lowest-location2-from-test-input
  (testing "should return 35 when apply to test input"
    (is (= (get-lowest-location2 (slurp "./resources/test-input.txt")) 35))))

(deftest get-lowest-location2-from-real-input
  (testing "should return ? when apply to real input"
    (is (= (get-lowest-location2 (slurp "./resources/input.txt")) 35))))
