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

(deftest get-card-index-test
  (testing "should return the index of the card"
    (is (= (get-card-index "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11") 6))
    (is (= (get-card-index "Card  45: 17 36 61 18 49 99 48 13 90 46 | 98 47 19 29 76 71 96 33 59 55 43 38 73  7 66 58 28 23 60  8 39 65 95 86 81") 45))
    ))


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


(deftest get-initial-carry-test
  (testing "should return a carry element with 1 for all the card numbers")
  (is (= (get-initial-carry ["Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
                             "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"
                             "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"
                             "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"
                             "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"
                             "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
                             ]) {1 1, 2 1, 3 1, 4 1, 5 1, 6 1}))
  )
(deftest get-points-from-a-card-test
  (testing "should return one copy each of the next four cards: cards 2, 3, 4, and 5. for the first card"
    (is (= (get-card-carry
             {1 1, 2 1, 3 1, 4 1, 5 1, 6 1}
             "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
             )
           {1 1, 2 2, 3 2, 4 2, 5 2, 6 1}))))

(deftest compute-all-the-final-cards-for-test-input
  (testing "should return the final amount of cards for the test input"
    (is (= (compute-all-cards test-input) 30)))
  )

(deftest test-total-cards
  (testing "should return the amount of cards"
    (is (= (get-cards-of-input-file "./resources/input.txt") 5329815))))