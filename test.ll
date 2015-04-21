(FUNCTION  doIfStatement  [(void void)]
  (BB 1
    (OPER 3 Func_Entry []  [])
  )
  (BB 2
    (OPER 4 Mov [(r 4)]  [(i 0)])
    (OPER 5 EQ [(r 3)]  [(r 2)(r 4)])
    (OPER 6 BEQ []  [(r 3)(i 0)(bb 5)])
  )
  (BB 3
    (OPER 7 Load [(r 6)]  [(s y)])
    (OPER 8 Mov [(r 7)]  [(i 1)])
    (OPER 9 EQ [(r 5)]  [(r 6)(r 7)])
    (OPER 10 BEQ []  [(r 5)(i 0)(bb 8)])
  )
  (BB 6
    (OPER 11 Mov [(r 10)]  [(i 1)])
    (OPER 12 Mov [(r 8)]  [(r 10)])
  )
  (BB 7
    (OPER 16 Add_I [(r 15)]  [(r 8)(r 11)])
    (OPER 17 Mov [(r 2)]  [(r 15)])
  )
  (BB 4
  )
  (BB 0
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 8
    (OPER 13 Mov [(r 13)]  [(i 12)])
    (OPER 14 Mov [(r 11)]  [(r 13)])
    (OPER 15 Jmp []  [(bb 7)])
  )
  (BB 11
    (OPER 24 Mov [(r 24)]  [(i 4)])
    (OPER 25 Mov [(r 22)]  [(r 24)])
    (OPER 26 Jmp []  [(bb 10)])
  )
  (BB 5
    (OPER 18 Load [(r 17)]  [(s x)])
    (OPER 19 Mov [(r 18)]  [(i 2)])
    (OPER 20 EQ [(r 16)]  [(r 17)(r 18)])
    (OPER 21 BEQ []  [(r 16)(i 0)(bb 11)])
  )
  (BB 9
    (OPER 22 Mov [(r 21)]  [(i 3)])
    (OPER 23 Mov [(r 19)]  [(r 21)])
  )
  (BB 10
    (OPER 27 Jmp []  [(bb 4)])
  )
)
