(DATA  a)
(FUNCTION  addThem  [(int d) (int e)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Add_I [(r 4)]  [(r 1)(r 2)])
    (OPER 5 Mov [(r 3)]  [(r 4)])
    (OPER 6 Mov [(m RetReg)]  [(r 3)])
    (OPER 7 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  main  [(void void)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 7)]  [(i 5)])
    (OPER 5 Mov [(r 3)]  [(r 7)])
    (OPER 6 Mov [(r 2)]  [(r 3)])
    (OPER 7 Mov [(r 9)]  [(i 5)])
    (OPER 8 EQ [(r 8)]  [(r 2)(r 9)])
    (OPER 9 BEQ []  [(r 8)(i 0)(bb 6)])
  )
  (BB 4
    (OPER 10 Load [(r 10)]  [(s a)])
    (OPER 11 Load [(r 12)]  [(s a)])
    (OPER 12 Mov [(r 13)]  [(i 3)])
    (OPER 13 Add_I [(r 11)]  [(r 12)(r 13)])
    (OPER 14 Mov [(r 10)]  [(r 11)])
    (OPER 15 Store []  [(r 11)(s a)])
  )
  (BB 5
    (OPER 21 Mov [(r 16)]  [(i 0)])
    (OPER 22 Mov [(r 4)]  [(r 16)])
    (OPER 23 Mov [(r 17)]  [(i 1)])
    (OPER 24 Mov [(r 6)]  [(r 17)])
    (OPER 25 Mov [(r 19)]  [(i 8)])
    (OPER 26 LTE [(r 18)]  [(r 6)(r 19)])
    (OPER 27 BEQ []  [(r 18)(i 0)(bb 8)])
  )
  (BB 7
    (OPER 28 Add_I [(r 20)]  [(r 4)(r 6)])
    (OPER 29 Mov [(r 4)]  [(r 20)])
    (OPER 30 Mov [(r 22)]  [(i 1)])
    (OPER 31 Add_I [(r 21)]  [(r 6)(r 22)])
    (OPER 32 Mov [(r 6)]  [(r 21)])
    (OPER 33 Mov [(r 24)]  [(i 8)])
    (OPER 34 LTE [(r 23)]  [(r 6)(r 24)])
    (OPER 35 BNE []  [(r 23)(i 0)(bb 7)])
  )
  (BB 8
    (OPER 36 Mov [(r 26)]  [(i 3)])
    (OPER 37 Div_I [(r 25)]  [(r 4)(r 26)])
    (OPER 38 Mov [(r 5)]  [(r 25)])
    (OPER 39 Mov [(r 28)]  [(i 4)])
    (OPER 40 Mul_I [(r 27)]  [(r 5)(r 28)])
    (OPER 41 Mov [(r 4)]  [(r 27)])
    (OPER 42 Load [(r 29)]  [(s a)])
    (OPER 43 Pass []  [(r 29)])
    (OPER 44 Pass []  [(r 2)])
    (OPER 45 JSR []  [(s addThem)])
    (OPER 46 Mov [(r 30)]  [(m RetReg)])
    (OPER 47 Mov [(r 3)]  [(r 30)])
    (OPER 48 Mov [(r 31)]  [(i 56)])
    (OPER 49 Pass []  [(r 31)])
    (OPER 50 JSR []  [(s putchar)])
    (OPER 51 Mov [(r 32)]  [(m RetReg)])
    (OPER 52 Mov [(r 33)]  [(i 61)])
    (OPER 53 Pass []  [(r 33)])
    (OPER 54 JSR []  [(s putchar)])
    (OPER 55 Mov [(r 34)]  [(m RetReg)])
    (OPER 56 Add_I [(r 35)]  [(r 4)(r 3)])
    (OPER 57 Pass []  [(r 35)])
    (OPER 58 JSR []  [(s putchar)])
    (OPER 59 Mov [(r 36)]  [(m RetReg)])
    (OPER 60 Mov [(r 37)]  [(i 10)])
    (OPER 61 Pass []  [(r 37)])
    (OPER 62 JSR []  [(s putchar)])
    (OPER 63 Mov [(r 38)]  [(m RetReg)])
    (OPER 64 Mov [(r 39)]  [(i 0)])
    (OPER 65 Mov [(m RetReg)]  [(r 39)])
    (OPER 66 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 6
    (OPER 16 Load [(r 14)]  [(s a)])
    (OPER 17 Mov [(r 15)]  [(i 4)])
    (OPER 18 Mov [(r 14)]  [(r 15)])
    (OPER 19 Store []  [(r 15)(s a)])
    (OPER 20 Jmp []  [(bb 5)])
  )
)
