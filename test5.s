.data
.comm	a,4,4

.text
	.align 4
.globl  addThem
addThem:
addThem_bb1:
	movl	%EDI, %EAX
	movl	%ESI, %EDI
addThem_bb2:
	addl	%EDI, %EAX
	ret
.globl  main
main:
main_bb1:
	pushq	%R14
	pushq	%R15
main_bb2:
	movl	$5, %EAX
	movl	%EAX, %EDI
	movl	$5, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb5
main_bb3:
	movl	$3, %EAX
	movl	%EAX, a(%RIP)
main_bb4:
	movl	$0, %EAX
	movl	%EAX, %R14D
	movl	$1, %EAX
	movl	%EAX, %ESI
	movl	$8, %EAX
	cmpl	%EAX, %ESI
	jg	main_bb7
main_bb6:
	movl	%R14D, %EAX
	addl	%ESI, %EAX
	movl	%EAX, %R14D
	movl	$1, %EAX
	addl	%EAX, %ESI
	cmpl	$0, %EDX
	jne	main_bb6
main_bb7:
	movl	$3, %ESI
	movl	$0, %EDX
	movl	%R14D, %EAX
	idivl	%ESI, %EAX
	movl	$4, %ESI
	imull	%ESI, %EAX
	movl	%EAX, %R14D
	movl	a(%RIP), %EAX
	movl	%EAX, %EDI
	movl	%EDI, %ESI
	call	addThem
	movl	%R15D, %EAX
	addl	%R14D, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$12, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
	popq	%R14
	popq	%R15
	ret
main_bb5:
	movl	$4, %EAX
	movl	%EAX, a(%RIP)
	jmp	main_bb4
