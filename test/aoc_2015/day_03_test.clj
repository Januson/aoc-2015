(ns aoc-2015.day-03-test
  (:require 
    [clojure.java.io :as io]
    [midje.sweet :refer :all]))

(println "Day 03.")

(defn is-north [direction]
  (= \^ direction))

(defn is-east [direction]
  (= \> direction))

(defn is-south [direction]
  (= \v direction))

(defn is-west [direction]
  (= \< direction))

(defn move-north [location]
  {:x (:x location) :y (inc (:y location))})

(defn move-east [location]
  {:x (inc (:x location)) :y (:y location)})

(defn move-south [location]
  {:x (:x location) :y (dec (:y location))})

(defn move-west [location]
  {:x (dec (:x location)) :y (:y location)})

(defn move [location direction]
  (cond
    (is-north direction) (move-north location)
    (is-east direction) (move-east location)
    (is-south direction) (move-south location)
    (is-west direction) (move-west location)
    :else location))

(defn delivery
  ([directions]
   (delivery {:x 0 :y 0} directions))
  ([location directions]
     (set (reductions move location directions))))

(defn santa-delivery [directions]
     (count (delivery directions)))

(defn robo-delivery [directions]
  (count (into
      (delivery (take-nth 2 directions))
      (delivery (take-nth 2 (rest directions))))))

(facts "about Santa's delivering"
  (fact "moving"
    (move {:x 0 :y 0} \^) => {:x 0 :y 1}
    (move {:x 0 :y 0} \>) => {:x 1 :y 0}
    (move {:x 0 :y 0} \v) => {:x 0 :y -1}
    (move {:x 0 :y 0} \<) => {:x -1 :y 0}))

(def input (-> "aoc_2015/day_03/input.txt" io/resource slurp))

(facts "Acceptance"
  (fact "Santa delivery"
    (santa-delivery input) => 2565)
  (fact "Robo delivery"
    (robo-delivery input) => 2639))
