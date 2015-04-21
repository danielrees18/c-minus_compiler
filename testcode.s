.data
.comm	a,4,4

.text
	.align 4
.globl  addThem
addThem:
addThem_bb2:
	movl	%EDI, %EAX
	movl	%ESI, %EDI
addThem_bb3:
	addl	%EDI, %EAX
addThem_bb1:
	ret
.globl  putDigit
putDigit:
putDigit_bb2:
putDigit_bb3:
	movl	$48, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %EDI
	call	putchar
putDigit_bb1:
	ret
.globl  printInt
printInt:
printInt_bb2:
	pushq	%R13
	pushq	%R14
	pushq	%R15
	movl	%EDI, %R15D
printInt_bb3:
	movl	$0, %EAX
	movl	%EAX, %R13D
	movl	$10000, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb6
printInt_bb4:
	movl	$45, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$1, %EAX
	movl	%EAX, %EDI
	call	putDigit
	movl	%R14D, %EAX
printInt_bb1:
	popq	%R13
	popq	%R14
	popq	%R15
	ret
printInt_bb11:
	movl	$1, %EAX
	cmpl	%EAX, %R13D
	jne	printInt_bb10
printInt_bb12:
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb13:
	jmp	printInt_bb10
printInt_bb16:
	movl	$1, %EAX
	cmpl	%EAX, %R13D
	jne	printInt_bb15
printInt_bb17:
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb18:
	jmp	printInt_bb15
printInt_bb6:
	movl	$1000, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb8
printInt_bb7:
	movl	$1000, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$1000, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%R15D, %EDI
	subl	%EAX, %EDI
	movl	%EDI, %R15D
	movl	$1, %EAX
	movl	%EAX, %R13D
printInt_bb8:
	movl	$100, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb11
printInt_bb9:
	movl	$100, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$100, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%R15D, %EDI
	subl	%EAX, %EDI
	movl	%EDI, %R15D
	movl	$1, %EAX
	movl	%EAX, %R13D
printInt_bb10:
	movl	$10, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb16
printInt_bb14:
	movl	$10, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$10, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%R15D, %EDI
	subl	%EAX, %EDI
	movl	%EDI, %R15D
printInt_bb15:
	movl	%R15D, %EDI
	call	putDigit
	jmp	printInt_bb1
.globl  main
main:
main_bb2:
	pushq	%R12
	pushq	%R13
	pushq	%R14
	pushq	%R15
main_bb3:
	movl	$5, %EAX
	movl	$5, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb6
main_bb4:
	movl	$3, %EAX
	movl	%EAX, a(%RIP)
main_bb5:
	movl	$0, %EAX
	movl	%EAX, %R14D
	movl	$1, %EAX
	movl	%EAX, %R15D
	movl	$8, %EAX
	cmpl	%EAX, %R15D
	jg	main_bb8
main_bb7:
	movl	%R14D, %EAX
	addl	%R15D, %EAX
	movl	%EAX, %R14D
	movl	$1, %EAX
	movl	%R15D, %EDX
	addl	%EAX, %EDX
	movl	%EDX, %R15D
	movl	$8, %EAX
	cmpl	%EAX, %R15D
	jg	main_bb8
	cmpl	$0, %ESI
	jne	main_bb7
main_bb8:
	movl	$3, %ESI
	movl	$0, %EDX
	movl	%R14D, %EAX
	idivl	%ESI, %EAX
	movl	%EAX, %EDX
	movl	$4, %ESI
	movl	%EDX, %EAX
	imull	%ESI, %EAX
	movl	%EAX, %R14D
	movl	a(%RIP), %EAX
	movl	%EAX, %EDI
	movl	%EDI, %ESI
	call	addThem
	movl	%R13D, %R15D
	movl	$56, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$61, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	%R15D, %EAX
	addl	%R14D, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
	movl	%EAX, %R15D
	movl	$10, %EAX
	cmpl	%EAX, %R15D
	jge	main_bb10
main_bb9:
	movl	$48, %EAX
	addl	%R15D, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$1, %EAX
	movl	%R15D, %EDI
	addl	%EAX, %EDI
	movl	%EDI, %R15D
	movl	$10, %EAX
	cmpl	%EAX, %R15D
	jge	main_bb10
	cmpl	$0, %R12D
	jne	main_bb9
main_bb10:
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$67, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$83, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$3510, %EAX
	movl	%EAX, %EDI
	call	printInt
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
	movl	%EAX, %EDI
	movl	$1, %EAX
	movl	%EAX, %R15D
	movl	$1, %EAX
	movl	%EAX, %R14D
	movl	$0, %EAX
	movl	%EAX, %EDX
	movl	$0, %EAX
	movl	$0, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb13
main_bb11:
	movl	$0, %EAX
	cmpl	%EAX, %R15D
	jne	main_bb16
main_bb14:
	movl	$1, %EAX
	movl	%EAX, %R15D
main_bb12:
	movl	$10, %EAX
	cmpl	%EAX, %R15D
	jne	main_bb25
main_bb23:
	movl	$99, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
	movl	$108, %EAX
	movl	%EAX, %EDI
	call	putchar
main_bb24:
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
main_bb1:
	popq	%R13
	popq	%R14
	popq	%R15
	popq	%R12
	ret
main_bb6:
	movl	$4, %EAX
	movl	%EAX, a(%RIP)
	jmp	main_bb5
main_bb22:
	movl	$3, %EAX
	movl	%EAX, %R15D
	jmp	main_bb12
main_bb19:
	movl	$0, %EAX
	cmpl	%EAX, %EDX
	jne	main_bb22
main_bb20:
	movl	$10, %EAX
	movl	%EAX, %R15D
main_bb21:
	jmp	main_bb12
main_bb16:
	movl	$0, %EAX
	cmpl	%EAX, %R14D
	jne	main_bb19
main_bb17:
	movl	$2, %EAX
	movl	%EAX, %R15D
main_bb18:
	jmp	main_bb12
main_bb13:
	movl	$0, %EAX
	movl	%EAX, %R15D
	jmp	main_bb12
main_bb25:
	movl	$98, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$97, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$100, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$61, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	%R15D, %EDI
	call	printInt
	jmp	main_bb24
