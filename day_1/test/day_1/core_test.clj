(ns day-1.core-test
  (:require [clojure.test :refer :all]
            [day-1.core :refer :all]))

(deftest test-frist-line
  (testing "should return 12 for line 1abc2"
    (is (= (get-numbers-from "1abc2") 12)))
  )
(deftest test-second-line
  (testing "should return 38 for line pqr3stu8vwx"
    (is (= (get-numbers-from "pqr3stu8vwx") 38)))
  )

(deftest test-third-line
  (testing "should return 15 for line a1b2c3d4e5f"
    (is (= (get-numbers-from "a1b2c3d4e5f") 15)))
  )

(deftest test-fourth-line
  (testing "should return 77 for line treb7uchet"
    (is (= (get-numbers-from "treb7uchet") 77)))
  )

(deftest test-sum-up-multiple-lines
  (testing "should return the sum of all the inputlines"
    (is (= (get-sum-of-lines ["1abc2" "pqr3stu8vwx" "a1b2c3d4e5f" "treb7uchet"]) 142)))
  )

(deftest test-sum-lines-in-file
  (testing "should return the sum of all the inputlines of the file"
    (is (= (process-file "./resources/calibration_document.txt") 54630)))
  )

(deftest test-second-part-first-line
  (testing "should return 29 for line two1nine"
    (is (= (get-numbers-from "two1nine") 29)))
  )

;two1nine
;eightwothree
;abcone2threexyz
;xtwone3four
;4nineeightseven2
;zoneight234
;7pqrstsixteen
;In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.