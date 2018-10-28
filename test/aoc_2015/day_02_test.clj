(ns aoc-2015.day-02-test
  (:require 
    [clojure.java.io :as io]
    [midje.sweet :refer :all]))

(println "Day 02.")

(defn base [box]
  (* (:length box)
     (:width box)))

(defn front [box]
  (* (:width box)
     (:height box)))

(defn side [box]
  (* (:height box)
     (:length box)))

(defn surface [box]
  (* 2
     (+ (base box)
        (front box)
        (side box))))

(defn slack [box]
  (min (base box)
       (front box)
       (side box)))

(defn paper [box]
  (+ (surface box)
     (slack box)))

(defn smallest-perimeter [box]
  (* 2 (min
         (+ (:length box) (:width box))
         (+ (:width box) (:height box))
         (+ (:length box) (:height box)))))

(defn bow [box]
  (* (:length box)
     (:width box)
     (:height box)))

(defn ribbon [box]
  (+ (smallest-perimeter box)
     (bow box)))

(defn total [measure boxes]
  (reduce + (map measure boxes)))

(facts "about `gifts`"
  (fact "wrapping"
    (paper {:length 1 :width 1 :height 1}) => 7
    (paper {:length 2 :width 3 :height 4}) => 58
    (paper {:length 1 :width 1 :height 10}) => 43)
  (fact "ribbon"
    (ribbon {:length 2 :width 3 :height 4}) => 34)
  )

(defn to-int [s]
  (Integer/parseInt s))

(defn to-box [dimens]
  (let [splitted (clojure.string/split dimens #"x")]
   {:length (to-int (first splitted))
    :width (to-int (second splitted))
    :height (to-int (last splitted))}))

(def input (-> "aoc_2015/day_02/input.txt"
               io/resource
               io/reader
               line-seq))
(def boxes (map to-box input))

(facts "Acceptance"
  (fact "Paper"
    (total paper boxes) => 1606483)
  (fact "Ribbon"
    (total ribbon boxes) => 3842356)
)
