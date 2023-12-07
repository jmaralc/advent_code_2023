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
;([0 2] [2 2] [2 6] [4 1] [5 7] [6 3] [7 6] [9 2] [9 5])
;([0 2] [2 2] [2 6] [4 2] [6 4] [7 6] [9 2] [9 5])

(deftest detect-engine-characters-position
  (testing "should return the position of all the engine characters"
    (is (= (detect-engine-characters test-input) [[1,3] [3,6] [4,3],[5,5],[8,3],[8,5]])))
  )

(deftest detect-engine-numbers-position
  (testing "should return the position of all the engine numbers"
    (is (= (detect-engine-numbers  "467..114..") [[0,0] [0,1] [0,2],[0,5],[0,6],[0,7]])))
  )

(deftest compute-taxcab-distance
  (testing "should return the taxicab distance between two points"
    (is (= (taxicab-distance [0 0] [1 0]) 1))))

(deftest get-close-engine-numbers
  (testing "should return the numbers that are touching a engine character"
    (is (= (get-number-neighbours [1,3] [[0,0] [0,1] [0,2],[0,5],[0,6],[0,7]]) [[0,2]]))))

(deftest merge-consecutive-numbers
  (testing "should return just one numbers when the position of two numbers is consecutive"
    (is (= (neighbours-positions? [0,1] [0,2]) true))
    (is (= (neighbours-positions? [0,1] [0,3]) false))
    )
  )

(deftest get-initial-detected-engine-numbers
  (testing "should return the initial candidates for engine numbers")
  (is (= (get-candidate-numbers test-input) ["7" "3" "6" "7" "2" "7" "6" "5"]))
  )

(deftest get-whole-number-from-initial-position
  (testing "should return the whole number of a string")
  (is (= (get-whole-numbers ["467..114.."] [0 2]) [467]))
  )

(deftest get-final-engine-numbers
  (testing "should return the initial candidates for engine numbers")
  (is (= (get-part-numbers test-input) [467 35 633 617 592 755 664 598]))
  )


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