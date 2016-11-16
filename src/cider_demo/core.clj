(ns cider-demo.core)

;; About Me.
;; Larry Christensen
;; Senior Software Developer, ClientSuccess.com
;; Founder, CEO, CTO, Lead Architect, and Principle Developer at OrcPub.com

;;=====================================================================

;; 1. Why Emacs?
;;;; a. Top-notch presentation software
;;;; a. Tight integration with the REPL.
;;;; b. Emacs is written in Lisp, by Lispers, for Lispers.
;;;; c. Super-customizable with tons of Lisp and Clojure-oriented plugins.
;;;; d. Write your own plugins in Emacs Lisp.
;;;; e. Mouseless workflow.
;;;; f. Super-stable and lightweight even with a ton of plugins.
;;;; g. Basic command uniformity among different tools
;;;; h. Super-portable: use the same configuration on most platforms
;;;; i. Free (as in root beer and speech)
;;;; j. Tetris: M-x tetris

;;=====================================================================

;; 2. Navigation
;;;; a.
;;                              M->
;;                               ^
;;                               ^
;;                              C-p
;;                               ^
;; M-a <<<< C-a <<< M-b << C-b < _ > C-f >> M-f >>> C-e >>>> M-e
;;                               v
;;                              C-n
;;                               v
;;                               v
;;                              M-<

(def s "Some String")

(defn add [a b]
  (+ a b))

(defn cool-fn [a b]
  (let [c (+ a b 2)]
    (prn "This is a really cool function")
    (+ (* a b c)
       (* a b a))))

;;;; b. Jump to line number: M-g g
;;;; c. Do a command x y times: C-u y x, for example C-u 10 C-p moves cursor up 10 lines 

;;=====================================================================

;; 3. Selection
;;;; a. C-SPACE to set mark, navigate to the end point
;;;; b. (expand-region) C-= on an s-expr
;;;; c. Select whole buffer: C-h
;;;; d. Unselect: C-g

;;=====================================================================

;; 4. Moving Code
;;;; a. Cut current selection: C-w
;;;; b. Copy current selection: M-w
;;;; c. Paste from kill ring: C-y
;;;; d. Paste previous item from kill ring: M-y
;;;; e. Kill line (or sexp in paredit): C-k
;;;; f. Unwrap expression: M-s
;;;; g. Insert newline and indent: M-j

(def a "Some string")

(defn a-function
  [a] (first (reverse a)))

[1 2 3 4]
[5 6 7 8]

[]

;;=====================================================================

;; 5. REPL
;;;; a. Start an nREPL server and client in the current project with C-c M-j (or M-x cider-jack-in)
;;;; b. Connect to a running nREPL server with M-x cider-connect
;;;; c. Search previous/next command history: M-p, M-n
;;;; d. Kill pending evaluation: C-c C-c
;;;;
;;;; c. Quit repl: C-c C-q

;;=====================================================================

;; 6. Auto-complete. Enabled with CIDER (C-c M-j or M-x cider-jack-in)
;;;; a. M-TAB
;;;; b. (company-mode)

;;=====================================================================

;; 7. Windows.
;;;; a. Open a window to the right of the current window: C-x 3
;;;; b. Open a window below: C-x 2
;;;; c. Close other windows: C-x 1
;;;; d. Go to next window: C-x o

;;=====================================================================

;; 8. Buffers.
;;;; a. Create a new buffer: C-x b
;;;; b. Open a buffer from file: C-x C-f
;;;; c. Save a buffer: C-x C-s
;;;; d. List buffers: C-x C-b
;;;; e. Kill a buffer: C-x k

;;=====================================================================

;; 9. Code Navigation.
;;;; a. (CIDER) Step into a function with M-.
;;;; b. (CIDER) Step out with M-,
;;;; c. (Paredit) Forward s-expr: C-M-f
;;;; d. (Paredit) Back s-expr: C-M-b
;;;; e. (Paredit) Move up se-expr: C-M-u

(frequencies [1 2 3 4 5 1 3 5 1])

(a-function (range -10 -120 -2))

;;=====================================================================

;; 10. Search
;;;; a. Regex search forward: C-s
;;;; b. Regex search backward: C-r
;;;; c. Replace string: M-x replace-string
;;;; d. Replace regex: M-x replace-regex
;;;;;; i. emails.edn

;;=====================================================================

;; 11. Interactive Development
;;;; a. Evaluate last sexp: C-c C-e
;;;; b. Macroexpand-1: C-c C-m
;;;; c. Macroexpand-all: C-c M-m

(when (= (+ 1 2) 4)
  (-> 4 inc inc))

;;;; d. Display documentation for symbol at point: C-c C-d C-d
;;;; e. Display JavaDoc: C-c C-d C-j
;;;; f. (which-key) Command completion

(def java-list (java.util.ArrayList.))
(reduce * (filter even? (range 2000000)))

;;=====================================================================

;; 12. Refactoring
;;;; a. expand-region, C-k, etc.
;;;; b. M-x string-replace, M-x regex-replace
;;;; c. clj-refactor

;;=====================================================================

;; 13. Debugging
;;;; a. Instrument a top-level form: C-u C-M-x
;;;; b. Run the instrumented code

(defn rad-fn [x y & args]
  (- (apply + x y args)
     (apply min x y args)))

(def rad-value (apply rad-fn 1 2 (map rand-int (range 6 22 2))))

(defn rad? [x]
  #dbg ^{:break/when (neg? x)}
  (= 23 (mod x 42)))

(defn radder-fn [x & [y z]]
  (reduce +
          (filter rad?
                  (map rand-int
                       (range x y z)))))

(def radder-value (radder-fn -10000 10000 2))

;;=====================================================================

;; 14. Enlighten mode
;;;; a. Enable: M-x cider-enlighten-mode
;;;; b. Evaluate functions to enlighten: C-c C-e

(defn raddest-fn [x y]
  (let [z (* x y)
        zz (+ x y)]
    (/ z zz)))

(def raddest-value (raddest-fn 3 4))

;;=====================================================================

;; 15. Testing
;;;; a. Run tests for current namespace using C-c C-t C-n. Tests in *ns* or *ns*-test are run.
;;;; b. Run tests for all loaded namespaces C-c C-t C-l
;;;; c. Run tests for all project namespaces C-c C-t C-l

;;=====================================================================

;; 16. Tracing
;;;; a. Enable tracing on a function with C-c M-t v
;;;; b. Call the function

(defn recursive-add [a b]
  (cond
    (neg? a) (recursive-add (inc a) (dec b))
    (zero? a) a
    (pos? a) (recursive-add (dec a) (inc b))))

(recursive-add 2 3)

(defn add-tree [t]
  (if (number? t)
    t
    (+ (if-let [l (:left t)] (add-tree l) 0)
       (if-let [r (:right t)] (add-tree r) 0))))

;;=====================================================================

;; 17. Source Control
;;;; a. VCS (C-x v ..., or click Git:... below)
;;;;;; i. Show log: C-x v l
;;;; b. Magit
;;;;;; i. Show Magit status: C-x g
;;;;;; ii. Navigate through sections with p & n
;;;;;; iii. Expand/collapse sections with TAB
;;;;;; iv. Stage and unstage changes with s & u
;;;;;; v. Commit with c, c
;;;;;; vi. Enter commit message
;;;;;; vii. C-c C-c to perform commit
;;;;;; viii. h for help
;;;;;; ix. q for quit

;;=====================================================================

;; 18. Markdown
;;;; a. README.md

;;=====================================================================

;; 19. Setup
;;;; a. Install base Emacs
;;;; b. Add ~/.emacs.d to Git
;;;; c. Set executable path
;;;; c. Set Melpa repo
;;;; d. Refresh package list: M-x package-refresh-contents
;;;; c. Add plugins
;;;;;; i. paredit (ensures parenthesis stay matched)
;;;;;; ii. expand-region (easy selection of code regions)
;;;;;; iii. company (code-completion)
;;;;;; iv. cider (Clojure IDE)
;;;;;; v. icomplete (completion in mini-buffer)
;;;;;; vi. rainbow-delimiters (colorful delimiters for visual matching)
;;;;;; vii. aggressive-indent (indents while you edit)
;;;;;; viii. which-key (command completion hints)



