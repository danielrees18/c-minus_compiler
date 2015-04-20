(FUNCTION  doAddition  [(void void)]
  (BB 1
    (OPER 3 Func_Entry []  [])
  )
  (BB 2
    (OPER 4 Mov [(r 5)]  [(i 4)])
    (OPER 5 Mov [(r 3)]  [(r 5)])
    (OPER 6 Mov [(r 8)]  [(i 5)])
    (OPER 7 Add_I [(r 7)]  [(r 3)(r 8)])
    (OPER 8 Mov [(r 2)]  [(r 7)])
    (OPER 9 Mov [(m RetReg)]  [(r 2)])
    (OPER 10 Jmp []  [(bb 0)])
  )
  (BB 0
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
