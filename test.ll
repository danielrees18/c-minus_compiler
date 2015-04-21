(FUNCTION  doLoop  [(void void)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Load [(r 4)]  [(s a)])
    (OPER 5 Mov [(r 5)]  [(i 0)])
    (OPER 6 EQ [(r 3)]  [(r 4)(r 5)])
    (OPER 7 BEQ []  [(r 3)(i 0)(bb 5)])
  )
  (BB 4
    (OPER 8 Mov [(r 6)]  [(r 2)])
  )
  (BB 5
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
