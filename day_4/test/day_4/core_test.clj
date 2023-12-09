(ns day-4.core-test
  (:require [clojure.test :refer :all]
            [day-4.core :refer :all]))

(def test-input "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")


(deftest test-total-points-from-input-file
  (testing "should return the sum of the points of each card in a given file"
    (is (= (get-points-of-input-file "./resources/input.txt") 21105))))

(deftest test-total-points-from-test-input
  (testing "should return the sum of the points of each card in a given game"
    (is (= (compute-total-points test-input) 13))))

(deftest split-game-in-cards-test
  (testing "should return the different cards value groups"
    (is (= (split-game-in-cards test-input)
           ["Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
            ]))))

(deftest get-number-of-a-card-test
  (testing "should return two values, one for the wining numbers one for y numbers"
    (is (= (get-numbers "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53") [(set [41 48 83 86 17])  (set [83 86  6 31 17  9 48 53]) ]))))


(deftest get-points-from-a-card-test
  (testing "should return 8 points for the first card"
    (is (= (get-card-points "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53") 8))))

(deftest get-points-from-a-card-test2
  (testing "should return 2 points for the second card"
    (is (= (get-card-points "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19") 2))))

(deftest get-points-from-a-card-test3
  (testing "should return 2 points for the third card"
    (is (= (get-card-points "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1") 2))))

(deftest get-points-from-a-card-test4
  (testing "should return 1 points for the fourth card"
    (is (= (get-card-points "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83") 1))))

(deftest get-points-from-a-card-test5
  (testing "should return 0 points for the fifth card"
    (is (= (get-card-points "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36") 0))))

(deftest get-points-from-a-card-test6
  (testing "should return 0 points for the sixth card"
    (is (= (get-card-points "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11") 0))))