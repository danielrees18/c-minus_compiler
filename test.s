.text
	.align 4
.globl  doIfStatement
doIfStatement:
doIfStatement_bb1:
doIfStatement_bb2:
	movl	$0, %EAX
	cmpl	%EAX, %EDX
	jne	doIfStatement_bb5
doIfStatement_bb3:
	movl	y(%RIP), %EDX
	movl	$1, %EAX
	cmpl	%EAX, %EDX
	jne	doIfStatement_bb8
doIfStatement_bb6:
	movl	$1, %EAX
	movl	%EAX, %ESI
doIfStatement_bb7:
	movl	%ESI, %EAX
	addl	%EDI, %EAX
doIfStatement_bb4:
	movl	$999999, %EAX
	ret
doIfStatement_bb8:
	movl	$12, %EAX
	movl	%EAX, %EDI
	jmp	doIfStatement_bb7
doIfStatement_bb11:
	movl	$4, %EAX
	jmp	doIfStatement_bb4
doIfStatement_bb5:
	movl	x(%RIP), %EDI
	movl	$2, %EAX
	cmpl	%EAX, %EDI
	jne	doIfStatement_bb11
doIfStatement_bb9:
	movl	$3, %EAX
doIfStatement_bb10:
	jmp	doIfStatement_bb4
