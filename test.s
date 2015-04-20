.text
	.align 4
.globl  doAddition
doAddition:
doAddition_bb1:
doAddition_bb2:
	movl	$4, %EAX
	movl	%EAX, %EDI
	movl	$5, %EAX
	addl	%EAX, %EDI
	movl	%EDI, %EAX
	ret
