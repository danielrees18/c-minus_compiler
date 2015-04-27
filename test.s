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
.globl  main
main:
main_bb2:
	pushq	%R14
	pushq	%R15
main_bb3:
	movl	$5, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	movl	$5, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb6
main_bb4:
	movl	a(%RIP), %ESI
	movl	$3, %EAX
	addl	%EAX, %ESI
	movl	%ESI, a(%RIP)
main_bb5:
	movl	$0, %EAX
	movl	%EAX, %R15D
	movl	$1, %EAX
	movl	%EAX, %ESI
	movl	$8, %EAX
	cmpl	%EAX, %ESI
	jg	main_bb8
main_bb7:
	movl	%R15D, %EAX
	addl	%ESI, %EAX
	movl	%EAX, %R15D
	movl	$1, %EAX
	addl	%EAX, %ESI
	movl	$8, %EAX
	cmpl	%EAX, %ESI
	jle	main_bb7
main_bb8:
	movl	$3, %ESI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%ESI, %EAX
	movl	$4, %ESI
	imull	%ESI, %EAX
	movl	%EAX, %R15D
	movl	a(%RIP), %EAX
	movl	%EAX, %EDI
	movl	%EDI, %ESI
	call	addThem
	movl	%EAX, %R14D
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
main_bb1:
	popq	%R14
	popq	%R15
	ret
main_bb6:
	movl	$4, %EAX
	movl	%EAX, a(%RIP)
	jmp	main_bb5
