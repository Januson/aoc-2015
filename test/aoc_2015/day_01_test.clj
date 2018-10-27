(ns aoc-2015.day-01-test
  (:require 
    [clojure.java.io :as io]
    [midje.sweet :refer :all]
    [aoc-2015.core :refer :all]))

(println "Day 01.")

(defn to-steps [directions]
  (map
    #(cond
      (= % \() 1
      (= % \)) -1
      :else 0)
    directions))

(defn floor [directions]
  (reduce +
    (to-steps directions)))

(defn basement [directions]
  (+ 1 (count
    (take-while
      #(<= 0 %)
      (reductions +
        (to-steps directions))))))

(facts "about `first part`"
  (fact "santa goes up on opening braces"
    (floor "(") => 1
    (floor "((") => 2)
  (fact "santa goes down on closing braces"
    (floor ")") => -1
    (floor "))") => -2))

(facts "about `second part`"
  (fact "santa enters the basement"
    (basement ")") => 1
    (basement "(()))") => 5))
 
(def input (-> "aoc_2015/day_01/input.txt" io/resource slurp))

(facts "Acceptance"
  (fact "First"
    (floor input) => 280)
  (fact "Second"
    (basement input) => 1797))
