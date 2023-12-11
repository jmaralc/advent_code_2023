(ns day-6.core-test
  (:require [clojure.test :refer :all]
            [day-6.core :refer :all]))

(def test-input "Time:      7  15   30
Distance:  9  40  200")

(def test-input-2 "Time:      71530
Distance:  940200")

(deftest split-input-test
  (testing "should split the input in time and distance components"
    (is (= (split-input test-input)) ["Time:      7  15   30" "Distance:  9  40  200"])))

(deftest extract-press-time-test
  (testing "should return an array of press times"
    (is (= (extract-press-time "Time:      7  15   30") [7 15 30]))))

(deftest extract-press-time-part2-test
  (testing "should return an array of press times"
    (is (= (extract-press-time-part2 "Time:      7  15   30") [71530]))))


(deftest extract-max-dist-test
  (testing "should return an array of press times"
    (is (= (extract-max-dist "Distance:  9  40  200") [9 40 200]))))

(deftest extract-max-dist-part2-test
  (testing "should return an array of press times"
    (is (= (extract-max-dist-part2 "Distance:  9  40  200") [940200]))))


(deftest combine-press-time-and-max-distance-test
  (testing "should return an array with pairs of press time and max distance"
    (is (= (combine-press-time-and-max-distance [7 15 30] [9 40 200]) [[7 9] [15 40] [30 200]]))
    ))

(deftest compute-ways-to-beat-record-test
  (testing "should return 4 for the first race of test input"
    (is (= (compute-ways-to-beat-record [7 9] ) 4))
    ))
(deftest compute-ways-to-beat-record-test
  (testing "should return 8 for the first race of test input"
    (is (= (compute-ways-to-beat-record [15 40] ) 8))
    ))
(deftest compute-ways-to-beat-record-test
  (testing "should return 9 for the first race of test input"
    (is (= (compute-ways-to-beat-record [30 200] ) 9))
    ))

(deftest compute-multiply-of-races-posibilities-test-input
  (testing "should return 288for for the test input"
    (is (= (compute-multiply-of-races-posibilities test-input ) 288))
    ))

(deftest compute-multiply-of-races-posibilities-test-input
  (testing "should return 288for for the test input"
    (is (= (compute-multiply-of-races-posibilities (slurp "./resources/input.txt") ) 503424))
    ))


(deftest compute-multiply-of-races-posibilitie-part2-test-input
  (testing "should return 288for for the test input"
    (is (= (compute-multiply-of-races-posibilities-part2 test-input ) 71503))
    ))
(deftest compute-multiply-of-races-posibilities-part2-test-input
  (testing "should return 288for for the test input"
    (is (= (compute-multiply-of-races-posibilities-part2 (slurp "./resources/input.txt") ) 32607562))
    ))
