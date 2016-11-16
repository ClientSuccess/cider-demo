(ns cider-demo.core-test
  (:require [clojure.test :refer :all]
            [cider-demo.core :refer [add]]))

(deftest test-add
  (is (= 3 (add 1 2)))
  (is (= 4 (add 2 3))))
