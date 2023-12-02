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

;(deftest test-sum-lines-in-file
;  (testing "should return the sum of all the inputlines of the file"
;    (is (= (process-file "./resources/calibration_document.txt") 54630)))
;  )

(deftest test-second-part-first-line
  (testing "should return 29 for line two1nine"
    (is (= (get-numbers-from "two1nine") 29)))
  )

(deftest test-second-part-second-line
  (testing "should return 83 for line eightwothree"
    (is (= (get-numbers-from "eightwothree") 83)))
  )

(deftest test-second-part-third-line
  (testing "should return 13 for line abcone2threexyz"
    (is (= (get-numbers-from "abcone2threexyz") 13)))
  )

(deftest test-second-part-fourth-line
  (testing "should return 24 for line xtwone3four"
    (is (= (get-numbers-from "xtwone3four") 24)))
  )

(deftest test-second-part-fith-line
  (testing "should return 4 for line 4nineeightseven2"
    (is (= (get-numbers-from "4nineeightseven2") 42)))
  )

(deftest test-second-part-sixth-line
  (testing "should return 14 for line zoneight234"
    (is (= (get-numbers-from "zoneight234") 14)))
  )

(deftest test-second-part-sixth-line
  (testing "should return 76 for line 7pqrstsixteen"
    (is (= (get-numbers-from "7pqrstsixteen") 76)))
  )


(deftest test-second-part-random-line
  (testing "should return 27 for line ljtwonefivesjninetpzhsbfxthree2vknbffmq7d"
    (is (= (get-numbers-from "ljtwonefivesjninetpzhsbfxthree2vknbffmq7d") 27)))
  )
(deftest test-second-part-random-line
  (testing "should return 82 for line nshfeight6eightonenineeighttwo"
    (is (= (get-numbers-from "nshfeight6eightonenineeighttwo") 82)))
  )

(deftest test-second-part-random-line
  (testing "should return 78 for line 7fiveeightoneightvs"
    (is (= (get-numbers-from "7fiveeightoneightvs") 78)))
  )

(deftest test-second-part-sum-up-multiple-lines
  (testing "should return the sum of all the inputlines"
    (is (= (get-sum-of-lines [
                              "two1nine"
                              "eightwothree"
                              "abcone2threexyz"
                              "xtwone3four"
                              "4nineeightseven2"
                              "zoneight234"
                              "7pqrstsixteen"
                              ]) 281)))
  )

(deftest test-second-part-sum-up-multiple-lines-tricky
  (testing "should return the sum of all the inputlines"
    (is (= (get-sum-of-lines [
                              "soneightninetwo161vhmf"      ;11 91
                              "voneightcqkcdvhxh4eight"     ;18 48
                              "9qzbqxmqonefiveknrnzpxoneightrq" ;91 95
                              "4btqghfcqx25fivetwo95oneightxf" ;41 45
                              "bgoneightkhgvqbfivefour1seven" ;17 57
                              "tvoneight3xtbvffvthreezcbrgk85eightsixbdgqspftkr" ;16 36
                              "15qhpvsevensixoneightt"      ;11 16
                              "7fiveeightoneightvs"         ;71 78
                              "fivesevenfour9jslninesevenjtttt7oneightssr" ;51 57
                              "koneightkk7dbtkdmmbf"        ;17 77
                              "4ssskfrfqhz9eightfour37oneightjm" ;41 47
                              "25sixjrjqgl5fivekhtxstwovgxzfpvzfmoneightb" ;21 22
                              "onetwonine4noneightvk"       ;11 14
                              "honeight5one"                ;11 51
                              "mzoneight9995five2bdg"       ;12 92
                              ]) 496)))
  )


(deftest test-sum-lines-in-file
  (testing "should return the sum of all the inputlines of the file"
    (is (= (process-file "./resources/input.txt") 54770)))
  )