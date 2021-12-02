(ns aoc.day-02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn ->int [s]
  (Integer/parseInt s))

;; Part 1
(defn find-end-position
  "Calculate the horizontal position and depth"
  [lines]
  (reduce (fn [[x y] [dir value]]
            (let [num-v (->int value)]
              (case dir
                "up"      [x (- y num-v)]
                "down"    [x (+ y num-v)]
                "forward" [(+ x num-v) y])))
          [0 0]
          lines))



(defn solution-1 [file-name]
  (with-open [reader (io/reader (io/resource file-name))]
    (->> (line-seq reader)
         (map #(str/split % #" "))
         (find-end-position)
         (apply *))))


;; Part 2
(defn find-end-position-with-aim
  "Calculate the horizontal position and depth with new rules"
  [lines]
  (reduce (fn [[x y aim] [dir value]]
            (let [num-v (->int value)]
              (case dir
                "up"      [x y (- aim num-v)]
                "down"    [x y (+ aim num-v)]
                "forward" [(+ x num-v) (+ y (* aim num-v)) aim])))
          [0 0 0]
          lines))

(defn solution-2 [file-name]
  (with-open [reader (io/reader (io/resource file-name))]
    (->> (line-seq reader)
         (map #(str/split % #" "))
         (find-end-position-with-aim)
         (butlast)
         (apply *))))



(comment
  (solution-1 "day_02.txt");; => 2120749
  (solution-2 "day_02.txt");; => 2138382217
)
