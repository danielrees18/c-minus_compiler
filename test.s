.text
	.align 4
.globl  doLoop
doLoop:
doLoop_bb1:
doLoop_bb2:
	movl	$0, %EAX
	cmpl	%EAX, %EDI
	jne	doLoop_bb4
doLoop_bb3:
	movl	$1, %EAX
	addl	%EAX, %EDI
	cmpl	$0, %ESI
	jne	doLoop_bb3
doLoop_bb4:
	movl	%EDI, %EAX
	ret
