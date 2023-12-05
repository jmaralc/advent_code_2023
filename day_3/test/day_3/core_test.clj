(ns day-3.core-test
  (:require [clojure.test :refer :all]
            [day-3.core :refer :all]))

(def test-input
  "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598.."
  )

(deftest detect-engine-characters-position
  (testing "should return the position of all the engine characters"
    (is (= (detect-engine-characters test-input) [[1,3] [3,6] [4,3],[5,5],[8,3],[8,5]])))
  )

(deftest detect-engine-numbersposition
  (testing "should return the position of all the engine numbers"
    (is (= (detect-engine-numbers  "467..114..") [[0,0] [0,1] [0,2],[0,5],[0,6],[0,7]])))
  )
;
;(deftest find-in-horizontal-1
;  (testing "should detect numbers in the first row independent of the second"
;    (is (= (detect-numbers-in-line "617*......") [617])))
;  )
;
;(deftest find-in-horizontal-2
;  (testing "should detect numbers in the first row independent of the second for second case"
;    (is (= (detect-numbers-in-line ".....+.58.") [58])))
;  )
;
;(deftest find-in-horizontal-3
;  (testing "should detect numbers in the first row independent of the second for third case"
;    (is (= (detect-numbers-in-line "...$.*....") [])))
;  )
;
;(deftest find-in-horizontal-4
;  (testing "should detect numbers in the first row independent of the second for fourth case"
;    (is (= (detect-numbers-in-line ".664.598..") [])))
;  )
;
;(deftest find-in-horizontal-5
;  (testing "should detect numbers in the first row independent of the second for fifth case"
;    (is (= (detect-numbers-in-line "467..114..") [])))
;  )

  ;467..114..
  ;...*......
  ;..35..633.
  ;......#...
  ;617*......
  ;.....+.58.
  ;..592.....
  ;......755.
  ;...$.*....
  ;.664.598..