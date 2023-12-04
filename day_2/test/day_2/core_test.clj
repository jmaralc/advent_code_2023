(ns day-2.core-test
  (:require [clojure.test :refer :all]
            [day-2.core :refer :all]))


(deftest test-get-result-of-multiple-games
  (testing "should return the sum of ids of valid games from a test file"
    (is (= (process-file "./resources/test1.txt") 8)))
  )

(deftest test-get-result-of-multiple-games-whole-dataset
  (testing "should return the sum of ids of valid games from a real input"
    (is (= (process-file "./resources/input.txt") 2237)))
  )

(deftest test-get-game-id-if-valid
  (testing "should return the id of the game if the game is valid"
    (is (= (get-game-id "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue") 2))))

(deftest test-get-zero-id-if-invalid
  (testing "should return zero if the game is invalid"
    (is (= (get-game-id "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red") 0))))

(deftest test-game1-valid-game
  (testing "check game should game 1 is a valid game"
    (is (true? (is_game_valid " 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")))))


(deftest test-game1-valid-game
  (testing "check game should game 1 is a valid game"
    (is (true? (is_game_valid " 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")))))


(deftest test-game4-invalid-game
  (testing "check game should game 4 is a valid game"
    (is (false? (is_game_valid " 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")))))


(deftest test-valid-withdraw
  (testing "check withdraw is a valid withdraw "
    (is (= (is-withdraw-possible " 1 red, 2 green, 6 blue") true))))


(deftest test-invalid-withdraw
  (testing "check line 3 withdraw is a invalid withdraw "
    (is (= (is-withdraw-possible " 8 green, 6 blue, 20 red") false))))



(deftest test-assign-color
  (testing "assign-color should update default withdraw "
    (is (= 
         (assign-color {"red" 0 "green" 0 "blue" 0} " 1 red")
         {"red" 1 "green" 0 "blue" 0}))))


(deftest test-compute-the-power
  (testing "should provide the power of a withdraw for game 1"
    (is (= (compute-game-power " 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green") 48))
    ))

(deftest test-compute-the-power
  (testing "should provide the power of a withdraw for game 3"
    (is (= (compute-game-power " 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red") 1560))
    ))

(deftest test-get-result-of-multiple-games
  (testing "should return the sum of ids of valid games from a test file"
    (is (= (get-powers-of-file "./resources/test1.txt") 2286)))
  )


(deftest test-get-result-of-multiple-games
  (testing "should return the sum of ids of valid games from a real input file"
    (is (= (get-powers-of-file "./resources/input.txt") 66681)))
  )