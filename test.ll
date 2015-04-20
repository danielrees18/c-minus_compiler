(FUNCTION  doAddition  [(void void)]
  (BB 1
    (OPER 3 Func_Entry []  [])
  )
  (BB 2
    (OPER 4 Mov [(r 2)]  [(i 5)])
    (OPER 5 Return [(r RetReg)]  [(r 2)])
    (OPER 6 Jmp []  [(bb 0)])
  )
  (BB 0
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
