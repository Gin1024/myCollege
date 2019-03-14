.8086
.MODEL SMALL
.STACK
.DATA
        DATA1 BYTE 3FH,06H,5BH,4FH,66H,6DH,7DH,07H,7FH,6FH,77H,7CH,39H,5EH,79H,71H
        DATA2 BYTE ?
.CODE
START:
        MOV AX,@DATA
        MOV DS,AX
        MOV AL,10001001B
        MOV DX,206H
        OUT DX,AL
LP:
        MOV DX,204H
        IN AL,DX
        MOV DATA2,AL
        AND AL,0F0H
        MOV CL,4
        SHR AX,CL
        MOV BX,OFFSET DATA1
        XLAT
        MOV BL,AL
        MOV AL,00000010B
        MOV DX,202H
        OUT DX,AL
        MOV AL,BL
        MOV DX,200H
        OUT DX,AL
        
        MOV CL,64H
        LOOP $
        
        MOV AL,DATA2
        AND AL,0FH
        MOV BX,OFFSET DATA1
        XLAT
        MOV BL,AL
        MOV AL,00000001B
        MOV DX,202H
        OUT DX,AL
        MOV AL,BL
        MOV DX,200H
        OUT DX,AL
        
        MOV CL,64H
        LOOP $
        JMP LP
        JMP $
END START