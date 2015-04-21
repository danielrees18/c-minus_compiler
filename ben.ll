(FUNCTION  test  [(void void)]
  (BB 1
    (OPER 3 Func_Entry []  [])
  )
  (BB 2
    (OPER 4 Mov [(r 5)]  [(i 0)])
    (OPER 5 Mov [(r 2)]  [(r 5)])
    (OPER 6 Mov [(r 7)]  [(i 1)])
    (OPER 7 Mov [(r 3)]  [(r 7)])
    (OPER 8 Mov [(r 10)]  [(i 0)])
    (OPER 9 Mov [(r 3)]  [(r 10)])
    (OPER 10 Mov [(r 2)]  [(r 9)])
    (OPER 11 GT [(r 11)]  [(r 2)(r 3)])
    (OPER 12 BEQ []  [(r 11)(i 0)(bb 5)])
  )
  (BB 3
    (OPER 13 Mov [(r 14)]  [(i 1)])
    (OPER 14 Sub_I [(r 13)]  [(r 3)(r 14)])
    (OPER 15 Mov [(r 3)]  [(r 13)])
  )
  (BB 4
    (OPER 41 EQ [(r 33)]  [(r 2)(r 3)])
    (OPER 42 BEQ []  [(r 33)(i 0)(bb 14)])
  )
  (BB 13
    (OPER 43 EQ [(r 34)]  [(r 2)(r 3)])
    (OPER 44 BEQ []  [(r 34)(i 0)(bb 16)])
  )
  (BB 15
    (OPER 45 EQ [(r 35)]  [(r 2)(r 3)])
    (OPER 46 BEQ []  [(r 35)(i 0)(bb 18)])
  )
  (BB 17
    (OPER 47 Mov [(r 38)]  [(i 1)])
    (OPER 48 Sub_I [(r 37)]  [(r 3)(r 38)])
    (OPER 49 Mov [(r 2)]  [(r 37)])
    (OPER 50 BNE []  [(r 35)(i 0)(bb 17)])
  )
  (BB 18
    (OPER 51 BNE []  [(r 34)(i 0)(bb 15)])
  )
  (BB 16
  )
  (BB 14
    (OPER 52 Mov [(r 40)]  [(i 3)])
    (OPER 53 Mov [(r 3)]  [(r 40)])
    (OPER 54 Mov [(m RetReg)]  [(r 41)])
    (OPER 55 Jmp []  [(bb 0)])
  )
  (BB 0
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 10
    (OPER 29 Mov [(r 27)]  [(i 1)])
    (OPER 30 Mov [(r 3)]  [(r 27)])
    (OPER 31 Mov [(r 29)]  [(i 1)])
    (OPER 32 EQ [(r 28)]  [(r 3)(r 29)])
    (OPER 33 BEQ []  [(r 28)(i 0)(bb 12)])
  )
  (BB 11
    (OPER 34 Mov [(r 32)]  [(i 2)])
    (OPER 35 Add_I [(r 31)]  [(r 3)(r 32)])
    (OPER 36 Mov [(r 3)]  [(r 31)])
    (OPER 37 BNE []  [(r 28)(i 0)(bb 11)])
  )
  (BB 12
    (OPER 38 Jmp []  [(bb 9)])
  )
  (BB 5
    (OPER 16 Mov [(r 16)]  [(i 2)])
    (OPER 17 Mov [(r 3)]  [(r 16)])
    (OPER 18 Mov [(r 18)]  [(i 2)])
    (OPER 19 EQ [(r 17)]  [(r 3)(r 18)])
    (OPER 20 BEQ []  [(r 17)(i 0)(bb 7)])
  )
  (BB 6
    (OPER 21 Mov [(r 20)]  [(i 2)])
    (OPER 22 Mov [(r 3)]  [(r 20)])
    (OPER 23 Mov [(r 22)]  [(i 2)])
    (OPER 24 EQ [(r 21)]  [(r 3)(r 22)])
    (OPER 25 BEQ []  [(r 21)(i 0)(bb 10)])
  )
  (BB 8
    (OPER 26 Mov [(r 25)]  [(i 1)])
    (OPER 27 Add_I [(r 24)]  [(r 3)(r 25)])
    (OPER 28 Mov [(r 3)]  [(r 24)])
  )
  (BB 9
    (OPER 39 BNE []  [(r 17)(i 0)(bb 6)])
  )
  (BB 7
    (OPER 40 Jmp []  [(bb 4)])
  )
)
