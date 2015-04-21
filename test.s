.data
.comm	z,4,4

.text
	.align 4
.globl  doAddition
doAddition:
doAddition_bb1:
	pushq	%R14
	pushq	%R15
doAddition_bb2:
	movl	$4, %EAX
	movl	%EAX, %EDI
	movl	$2, %EAX
	addl	%EAX, %EDI
	movl	%EDI, z(%RIP)
	movl	z(%RIP), %R15D
	movl	$9, %EAX
	movl	%EAX, %EDI
	call	getValue
	movl	%R15D, %EAX
	addl	%R14D, %EAX
	popq	%R14
	popq	%R15
	ret
.globl  getValue
getValue:
getValue_bb1:
	movl	%EDI, %EAX
getValue_bb2:
	ret
