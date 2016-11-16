(ns cider-demo.core-test
  (:require [clojure.test :refer :all]

            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            
            [cider-demo.core :refer [recursive-add]]))

(deftest test-recursive-add
  (is (= 3 (recursive-add 1 2))))

(defspec recursive-add--commutative-property
  100 ;; # of iterations
  (prop/for-all [[a b] (gen/tuple gen/int gen/int)]
                (= (recursive-add a b) (recursive-add b a))))

(defspec recursive-add--associative-property
  100 ;; # of iterations
  (prop/for-all [[a b c] (gen/tuple gen/int gen/int gen/int)]
                (= (recursive-add
                    (recursive-add a b)
                    c) ;; c + (a + b)
                   (recursive-add
                    a
                    (recursive-add b c)))))

(defspec recursive-add--additive-identity-property
  100 ;; # of iterations
  (prop/for-all [a gen/int]
                (and (= (recursive-add a 0) 5)
                     (= (recursive-add 0 a) 5))))
