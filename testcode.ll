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
(FUNCTION  putDigit  [(int s)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 3)]  [(i 48)])
    (OPER 5 Add_I [(r 2)]  [(r 3)(r 1)])
    (OPER 6 Pass []  [(r 2)])
    (OPER 7 JSR []  [(s putchar)])
    (OPER 8 Mov [(r 4)]  [(m RetReg)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  printInt  [(int r)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 4)]  [(i 0)])
    (OPER 5 Mov [(r 3)]  [(r 4)])
    (OPER 6 Mov [(r 6)]  [(i 10000)])
    (OPER 7 GTE [(r 5)]  [(r 1)(r 6)])
    (OPER 8 BEQ []  [(r 5)(i 0)(bb 6)])
  )
  (BB 4
    (OPER 9 Mov [(r 7)]  [(i 45)])
    (OPER 10 Pass []  [(r 7)])
    (OPER 11 JSR []  [(s putchar)])
    (OPER 12 Mov [(r 8)]  [(m RetReg)])
    (OPER 13 Mov [(r 9)]  [(i 1)])
    (OPER 14 Pass []  [(r 9)])
    (OPER 15 JSR []  [(s putDigit)])
    (OPER 16 Mov [(r 10)]  [(m RetReg)])
    (OPER 17 Mov [(m RetReg)]  [(r 11)])
    (OPER 18 Jmp []  [(bb 1)])
  )
  (BB 5
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 11
    (OPER 49 Mov [(r 31)]  [(i 1)])
    (OPER 50 EQ [(r 30)]  [(r 3)(r 31)])
    (OPER 51 BEQ []  [(r 30)(i 0)(bb 13)])
  )
  (BB 12
    (OPER 52 Mov [(r 32)]  [(i 0)])
    (OPER 53 Pass []  [(r 32)])
    (OPER 54 JSR []  [(s putDigit)])
    (OPER 55 Mov [(r 33)]  [(m RetReg)])
  )
  (BB 13
    (OPER 56 Jmp []  [(bb 10)])
  )
  (BB 16
    (OPER 70 Mov [(r 43)]  [(i 1)])
    (OPER 71 EQ [(r 42)]  [(r 3)(r 43)])
    (OPER 72 BEQ []  [(r 42)(i 0)(bb 18)])
  )
  (BB 17
    (OPER 73 Mov [(r 44)]  [(i 0)])
    (OPER 74 Pass []  [(r 44)])
    (OPER 75 JSR []  [(s putDigit)])
    (OPER 76 Mov [(r 45)]  [(m RetReg)])
  )
  (BB 18
    (OPER 77 Jmp []  [(bb 15)])
  )
  (BB 6
    (OPER 19 Mov [(r 13)]  [(i 1000)])
    (OPER 20 GTE [(r 12)]  [(r 1)(r 13)])
    (OPER 21 BEQ []  [(r 12)(i 0)(bb 8)])
  )
  (BB 7
    (OPER 22 Mov [(r 15)]  [(i 1000)])
    (OPER 23 Div_I [(r 14)]  [(r 1)(r 15)])
    (OPER 24 Mov [(r 2)]  [(r 14)])
    (OPER 25 Pass []  [(r 2)])
    (OPER 26 JSR []  [(s putDigit)])
    (OPER 27 Mov [(r 16)]  [(m RetReg)])
    (OPER 28 Mov [(r 19)]  [(i 1000)])
    (OPER 29 Mul_I [(r 18)]  [(r 2)(r 19)])
    (OPER 30 Sub_I [(r 17)]  [(r 1)(r 18)])
    (OPER 31 Mov [(r 1)]  [(r 17)])
    (OPER 32 Mov [(r 20)]  [(i 1)])
    (OPER 33 Mov [(r 3)]  [(r 20)])
  )
  (BB 8
    (OPER 34 Mov [(r 22)]  [(i 100)])
    (OPER 35 GTE [(r 21)]  [(r 1)(r 22)])
    (OPER 36 BEQ []  [(r 21)(i 0)(bb 11)])
  )
  (BB 9
    (OPER 37 Mov [(r 24)]  [(i 100)])
    (OPER 38 Div_I [(r 23)]  [(r 1)(r 24)])
    (OPER 39 Mov [(r 2)]  [(r 23)])
    (OPER 40 Pass []  [(r 2)])
    (OPER 41 JSR []  [(s putDigit)])
    (OPER 42 Mov [(r 25)]  [(m RetReg)])
    (OPER 43 Mov [(r 28)]  [(i 100)])
    (OPER 44 Mul_I [(r 27)]  [(r 2)(r 28)])
    (OPER 45 Sub_I [(r 26)]  [(r 1)(r 27)])
    (OPER 46 Mov [(r 1)]  [(r 26)])
    (OPER 47 Mov [(r 29)]  [(i 1)])
    (OPER 48 Mov [(r 3)]  [(r 29)])
  )
  (BB 10
    (OPER 57 Mov [(r 35)]  [(i 10)])
    (OPER 58 GTE [(r 34)]  [(r 1)(r 35)])
    (OPER 59 BEQ []  [(r 34)(i 0)(bb 16)])
  )
  (BB 14
    (OPER 60 Mov [(r 37)]  [(i 10)])
    (OPER 61 Div_I [(r 36)]  [(r 1)(r 37)])
    (OPER 62 Mov [(r 2)]  [(r 36)])
    (OPER 63 Pass []  [(r 2)])
    (OPER 64 JSR []  [(s putDigit)])
    (OPER 65 Mov [(r 38)]  [(m RetReg)])
    (OPER 66 Mov [(r 41)]  [(i 10)])
    (OPER 67 Mul_I [(r 40)]  [(r 2)(r 41)])
    (OPER 68 Sub_I [(r 39)]  [(r 1)(r 40)])
    (OPER 69 Mov [(r 1)]  [(r 39)])
  )
  (BB 15
    (OPER 78 Pass []  [(r 1)])
    (OPER 79 JSR []  [(s putDigit)])
    (OPER 80 Mov [(r 46)]  [(m RetReg)])
    (OPER 81 Jmp []  [(bb 5)])
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
    (OPER 10 Mov [(r 13)]  [(i 3)])
    (OPER 11 Add_I [(r 11)]  [(r 12)(r 13)])
    (OPER 12 Mov [(r 10)]  [(r 11)])
    (OPER 13 Store []  [(r 11)(s a)])
  )
  (BB 5
    (OPER 18 Mov [(r 16)]  [(i 0)])
    (OPER 19 Mov [(r 4)]  [(r 16)])
    (OPER 20 Mov [(r 17)]  [(i 1)])
    (OPER 21 Mov [(r 6)]  [(r 17)])
    (OPER 22 Mov [(r 19)]  [(i 8)])
    (OPER 23 LTE [(r 18)]  [(r 6)(r 19)])
    (OPER 24 BEQ []  [(r 18)(i 0)(bb 8)])
  )
  (BB 7
    (OPER 25 Add_I [(r 20)]  [(r 4)(r 6)])
    (OPER 26 Mov [(r 4)]  [(r 20)])
    (OPER 27 Mov [(r 22)]  [(i 1)])
    (OPER 28 Add_I [(r 21)]  [(r 6)(r 22)])
    (OPER 29 Mov [(r 6)]  [(r 21)])
    (OPER 30 Mov [(r 24)]  [(i 8)])
    (OPER 31 LTE [(r 23)]  [(r 6)(r 24)])
    (OPER 32 BNE []  [(r 23)(i 0)(bb 7)])
  )
  (BB 8
    (OPER 33 Mov [(r 26)]  [(i 3)])
    (OPER 34 Div_I [(r 25)]  [(r 4)(r 26)])
    (OPER 35 Mov [(r 5)]  [(r 25)])
    (OPER 36 Mov [(r 28)]  [(i 4)])
    (OPER 37 Mul_I [(r 27)]  [(r 5)(r 28)])
    (OPER 38 Mov [(r 4)]  [(r 27)])
    (OPER 39 Pass []  [(r 29)])
    (OPER 40 Pass []  [(r 2)])
    (OPER 41 JSR []  [(s addThem)])
    (OPER 42 Mov [(r 30)]  [(m RetReg)])
    (OPER 43 Mov [(r 3)]  [(r 0)])
    (OPER 44 Mov [(r 31)]  [(i 56)])
    (OPER 45 Pass []  [(r 31)])
    (OPER 46 JSR []  [(s putchar)])
    (OPER 47 Mov [(r 32)]  [(m RetReg)])
    (OPER 48 Mov [(r 33)]  [(i 61)])
    (OPER 49 Pass []  [(r 33)])
    (OPER 50 JSR []  [(s putchar)])
    (OPER 51 Mov [(r 34)]  [(m RetReg)])
    (OPER 52 Add_I [(r 35)]  [(r 3)(r 4)])
    (OPER 53 Pass []  [(r 35)])
    (OPER 54 JSR []  [(s putchar)])
    (OPER 55 Mov [(r 36)]  [(m RetReg)])
    (OPER 56 Mov [(r 37)]  [(i 10)])
    (OPER 57 Pass []  [(r 37)])
    (OPER 58 JSR []  [(s putchar)])
    (OPER 59 Mov [(r 38)]  [(m RetReg)])
    (OPER 60 Mov [(r 39)]  [(i 0)])
    (OPER 61 Mov [(r 6)]  [(r 39)])
    (OPER 62 Mov [(r 41)]  [(i 10)])
    (OPER 63 LT [(r 40)]  [(r 6)(r 41)])
    (OPER 64 BEQ []  [(r 40)(i 0)(bb 10)])
  )
  (BB 9
    (OPER 65 Mov [(r 43)]  [(i 48)])
    (OPER 66 Add_I [(r 42)]  [(r 43)(r 6)])
    (OPER 67 Pass []  [(r 42)])
    (OPER 68 JSR []  [(s putchar)])
    (OPER 69 Mov [(r 44)]  [(m RetReg)])
    (OPER 70 Mov [(r 46)]  [(i 1)])
    (OPER 71 Add_I [(r 45)]  [(r 6)(r 46)])
    (OPER 72 Mov [(r 6)]  [(r 45)])
    (OPER 73 Mov [(r 48)]  [(i 10)])
    (OPER 74 LT [(r 47)]  [(r 6)(r 48)])
    (OPER 75 BNE []  [(r 47)(i 0)(bb 9)])
  )
  (BB 10
    (OPER 76 Mov [(r 49)]  [(i 10)])
    (OPER 77 Pass []  [(r 49)])
    (OPER 78 JSR []  [(s putchar)])
    (OPER 79 Mov [(r 50)]  [(m RetReg)])
    (OPER 80 Mov [(r 51)]  [(i 67)])
    (OPER 81 Pass []  [(r 51)])
    (OPER 82 JSR []  [(s putchar)])
    (OPER 83 Mov [(r 52)]  [(m RetReg)])
    (OPER 84 Mov [(r 53)]  [(i 83)])
    (OPER 85 Pass []  [(r 53)])
    (OPER 86 JSR []  [(s putchar)])
    (OPER 87 Mov [(r 54)]  [(m RetReg)])
    (OPER 88 Mov [(r 55)]  [(i 3510)])
    (OPER 89 Pass []  [(r 55)])
    (OPER 90 JSR []  [(s printInt)])
    (OPER 91 Mov [(r 56)]  [(m RetReg)])
    (OPER 92 Mov [(r 57)]  [(i 10)])
    (OPER 93 Pass []  [(r 57)])
    (OPER 94 JSR []  [(s putchar)])
    (OPER 95 Mov [(r 58)]  [(m RetReg)])
    (OPER 96 Mov [(r 59)]  [(i 0)])
    (OPER 97 Mov [(r 2)]  [(r 59)])
    (OPER 98 Mov [(r 60)]  [(i 1)])
    (OPER 99 Mov [(r 3)]  [(r 60)])
    (OPER 100 Mov [(r 61)]  [(i 1)])
    (OPER 101 Mov [(r 4)]  [(r 61)])
    (OPER 102 Mov [(r 62)]  [(i 0)])
    (OPER 103 Mov [(r 5)]  [(r 62)])
    (OPER 104 Mov [(r 63)]  [(i 0)])
    (OPER 105 Mov [(r 6)]  [(r 63)])
    (OPER 106 Mov [(r 65)]  [(i 0)])
    (OPER 107 EQ [(r 64)]  [(r 2)(r 65)])
    (OPER 108 BEQ []  [(r 64)(i 0)(bb 13)])
  )
  (BB 11
    (OPER 109 Mov [(r 67)]  [(i 0)])
    (OPER 110 EQ [(r 66)]  [(r 3)(r 67)])
    (OPER 111 BEQ []  [(r 66)(i 0)(bb 16)])
  )
  (BB 14
    (OPER 112 Mov [(r 68)]  [(i 1)])
    (OPER 113 Mov [(r 6)]  [(r 68)])
  )
  (BB 15
  )
  (BB 12
    (OPER 132 Mov [(r 78)]  [(i 10)])
    (OPER 133 EQ [(r 77)]  [(r 6)(r 78)])
    (OPER 134 BEQ []  [(r 77)(i 0)(bb 25)])
  )
  (BB 23
    (OPER 135 Mov [(r 79)]  [(i 99)])
    (OPER 136 Pass []  [(r 79)])
    (OPER 137 JSR []  [(s putchar)])
    (OPER 138 Mov [(r 80)]  [(m RetReg)])
    (OPER 139 Mov [(r 81)]  [(i 0)])
    (OPER 140 Pass []  [(r 81)])
    (OPER 141 JSR []  [(s putDigit)])
    (OPER 142 Mov [(r 82)]  [(m RetReg)])
    (OPER 143 Mov [(r 83)]  [(i 0)])
    (OPER 144 Pass []  [(r 83)])
    (OPER 145 JSR []  [(s putDigit)])
    (OPER 146 Mov [(r 84)]  [(m RetReg)])
    (OPER 147 Mov [(r 85)]  [(i 108)])
    (OPER 148 Pass []  [(r 85)])
    (OPER 149 JSR []  [(s putchar)])
    (OPER 150 Mov [(r 86)]  [(m RetReg)])
  )
  (BB 24
    (OPER 171 Mov [(r 96)]  [(i 10)])
    (OPER 172 Pass []  [(r 96)])
    (OPER 173 JSR []  [(s putchar)])
    (OPER 174 Mov [(r 97)]  [(m RetReg)])
    (OPER 175 Mov [(r 98)]  [(i 0)])
    (OPER 176 Mov [(m RetReg)]  [(r 98)])
    (OPER 177 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 6
    (OPER 14 Mov [(r 15)]  [(i 4)])
    (OPER 15 Mov [(r 14)]  [(r 15)])
    (OPER 16 Store []  [(r 15)(s a)])
    (OPER 17 Jmp []  [(bb 5)])
  )
  (BB 22
    (OPER 124 Mov [(r 75)]  [(i 3)])
    (OPER 125 Mov [(r 6)]  [(r 75)])
    (OPER 126 Jmp []  [(bb 21)])
  )
  (BB 19
    (OPER 119 Mov [(r 73)]  [(i 0)])
    (OPER 120 EQ [(r 72)]  [(r 5)(r 73)])
    (OPER 121 BEQ []  [(r 72)(i 0)(bb 22)])
  )
  (BB 20
    (OPER 122 Mov [(r 74)]  [(i 10)])
    (OPER 123 Mov [(r 6)]  [(r 74)])
  )
  (BB 21
    (OPER 127 Jmp []  [(bb 18)])
  )
  (BB 16
    (OPER 114 Mov [(r 70)]  [(i 0)])
    (OPER 115 EQ [(r 69)]  [(r 4)(r 70)])
    (OPER 116 BEQ []  [(r 69)(i 0)(bb 19)])
  )
  (BB 17
    (OPER 117 Mov [(r 71)]  [(i 2)])
    (OPER 118 Mov [(r 6)]  [(r 71)])
  )
  (BB 18
    (OPER 128 Jmp []  [(bb 15)])
  )
  (BB 13
    (OPER 129 Mov [(r 76)]  [(i 0)])
    (OPER 130 Mov [(r 6)]  [(r 76)])
    (OPER 131 Jmp []  [(bb 12)])
  )
  (BB 25
    (OPER 151 Mov [(r 87)]  [(i 98)])
    (OPER 152 Pass []  [(r 87)])
    (OPER 153 JSR []  [(s putchar)])
    (OPER 154 Mov [(r 88)]  [(m RetReg)])
    (OPER 155 Mov [(r 89)]  [(i 97)])
    (OPER 156 Pass []  [(r 89)])
    (OPER 157 JSR []  [(s putchar)])
    (OPER 158 Mov [(r 90)]  [(m RetReg)])
    (OPER 159 Mov [(r 91)]  [(i 100)])
    (OPER 160 Pass []  [(r 91)])
    (OPER 161 JSR []  [(s putchar)])
    (OPER 162 Mov [(r 92)]  [(m RetReg)])
    (OPER 163 Mov [(r 93)]  [(i 61)])
    (OPER 164 Pass []  [(r 93)])
    (OPER 165 JSR []  [(s putchar)])
    (OPER 166 Mov [(r 94)]  [(m RetReg)])
    (OPER 167 Pass []  [(r 6)])
    (OPER 168 JSR []  [(s printInt)])
    (OPER 169 Mov [(r 95)]  [(m RetReg)])
    (OPER 170 Jmp []  [(bb 24)])
  )
)
