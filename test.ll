(FUNCTION  doLoop  [(void void)]
  (BB 1
    (OPER 3 Func_Entry []  [])
  )
  (BB 2
    (OPER 4 Mov [(r 4)]  [(i 0)])
    (OPER 5 EQ [(r 3)]  [(r 2)(r 4)])
    (OPER 6 BEQ []  [(r 3)(i 0)(bb 4)])
  )
  (BB 3
    (OPER 7 Mov [(r 7)]  [(i 1)])
    (OPER 8 Add_I [(r 6)]  [(r 2)(r 7)])
    (OPER 9 Mov [(r 2)]  [(r 6)])
    (OPER 10 BNE []  [(r 3)(i 0)(bb 3)])
  )
  (BB 4
    (OPER 11 Mov [(m RetReg)]  [(r 2)])
    (OPER 12 Jmp []  [(bb 0)])
  )
  (BB 0
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
