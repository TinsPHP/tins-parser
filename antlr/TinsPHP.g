/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */
 
 /* This file was created based on TSPHP.g - the grammar file of TSPHP's parser - and
  * reuses TSPHP's AST class as well as other classes/files related to the AST generation.
  * TSPHP is also licenced under the Apache License 2.0
  * For more information see http://tsphp.ch/wiki/display/TSPHP/License
  */

grammar TinsPHP;

options {
    output = AST;
    ASTLabelType = ITSPHPAst;
    tokenVocab = TSPHP;
}

tokens{
    Abstract = 'abstract';
    Arrow = '=>';
    As = 'as';
    Assign = '=';
    At = '@';
    Backslash = '\\';
    BitwiseAnd = '&';
    BitwiseAndAssign = '&=';
    BitwiseNot = '~';
    BitwiseOr = '|';
    BitwiseOrAssign = '|=';
    BitwiseXor = '^';
    BitwiseXorAssign = '^=';
    Break = 'break';
    Case = 'case';
    Cast = 'cast';
    Catch = 'catch';
    Class = 'class';
    Clone = 'clone';
    Colon = ':';
    Comma = ',';
    Const = 'const';
    Construct = '__construct';
    Continue = 'continue';
    Default = 'default';
    Destruct = '__destruct';
    Divide = '/';
    DivideAssign = '/=';
    Do = 'do';
    Dollar = '$';
    Dot = '.';
    DotAssign = '.=';
    DoubleColon = '::';
    Echo = 'echo';
    Else = 'else';
    Equal = '==';    
    Exit = 'exit';
    Extends = 'extends';
    Final = 'final';
    For = 'for';
    Foreach = 'foreach';
    Function = 'function';
    GreaterThan = '>';
    GreaterEqualThan = '>=';
    Identical = '===';
    If = 'if';
    Implements = 'implements';
    Instanceof = 'instanceof';
    Interface = 'interface';
    LeftCurlyBrace = '{';
    LeftParenthesis = '(';
    LeftSquareBrace = '[';
    LessThan = '<';
    LessEqualThan = '<=';
    LogicAnd = '&&';
    LogicAndWeak = 'and';
    LogicNot = '!';
    LogicOr = '||';
    LogicOrWeak = 'or';
    LogicXorWeak = 'xor';
    Minus = '-';
    MinusAssign = '-=';
    MinusMinus = '--';
    Modulo = '%';
    ModuloAssign = '%=';
    Multiply = '*';
    MultiplyAssign = '*=';
    Namespace = 'namespace';
    New = 'new';
    NotEqual = '!=';
    NotEqualAlternative = '<>';
    NotIdentical = '!==';
    Null = 'null';
    ObjectOperator = '->';
    Parent = 'parent';
    ParentColonColon = 'parent::';
    //start aditional tokens
    PhpStart = '<?php';
    PhpEnd = '?>';
    //end additional tokens
    Plus = '+';
    PlusAssign = '+=';
    PlusPlus = '++';
    Private = 'private';
    Protected = 'protected';
    ProtectThis = 'this';
    Public = 'public';
    QuestionMark = '?';
    Return = 'return';
    RightCurlyBrace = '}';
    RightParenthesis = ')';
    RightSquareBrace = ']';
    ShiftLeft = '<<';
    ShiftLeftAssign = '<<=';
    ShiftRight = '>>';
    ShiftRightAssign = '>>=';
    Static = 'static';
    This = '$this';
    Throw = 'throw';
    Try = 'try';
    TypeBool = 'bool';
    TypeAliasBool = 'boolean';
    TypeAliasFloat = 'double';
    TypeAliasFloat2 = 'real';
    TypeInt = 'int';
    TypeAliasInt = 'integer';
    TypeFloat = 'float';
    TypeString = 'string';
    TypeArray = 'array';
    TypeResource = 'resource';
    TypeObject = 'object';
    TypeMixed = 'mixed';
    Self = 'self';
    SelfColonColon = 'self::';
    Semicolon = ';';
    Switch = 'switch';
    Use = 'use';
    Void = 'void';
    While = 'while';

    // Imaginary tokens
    ACTUAL_PARAMETERS;
    ARRAY_ACCESS;
    BLOCK;
    BLOCK_CONDITIONAL;
    CAST;
    CAST_ASSIGN;
    
    CLASS_BODY;
    CLASS_MODIFIER;
    
    FIELD;
    FIELD_MODIFIER;
    FIELD_ACCESS;
    CLASS_STATIC_ACCESS;
    CLASS_STATIC_ACCESS_VARIABLE_ID;
    
    CONSTANT;
    CONSTANT_DECLARATION;
    CONSTANT_DECLARATION_LIST;
    
    DEFAULT_NAMESPACE;
    
    EXPRESSION;
    EXPRESSION_LIST;
    
    FUNCTION_CALL;
    FUNCTION_MODIFIER;
    
    INTERFACE_BODY;
    
    METHOD_CALL;
    METHOD_CALL_POSTFIX;
    METHOD_CALL_STATIC;
    METHOD_DECLARATION;
    METHOD_MODIFIER;
    
    NAMESPACE_BODY;
    
    PARAMETER_DECLARATION;
    PARAMETER_LIST;
    PARAMETER_TYPE;
    
    POST_INCREMENT;
    POST_DECREMENT;
    PRE_INCREMENT;
    PRE_DECREMENT;
    SWITCH_CASES;
    
    TYPE;
    TYPE_MODIFIER;
    TYPE_NAME;
    
    UNARY_MINUS;
    UNARY_PLUS;
    USE_DECLARATION;
    
    VARIABLE_DECLARATION;
    VARIABLE_DECLARATION_LIST;    
}

@header{
/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

package ch.tsphp.tinsphp.parser.antlr;

import ch.tsphp.common.AstHelperRegistry;
import ch.tsphp.common.ITSPHPAst;

}

@members{
private ITSPHPAst fieldModifiers;
private static final String BACKSLASH = "\\";
}

@lexer::header{
/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

package ch.tsphp.tinsphp.parser.antlr;
}

compilationUnit    
    :   '<?php'! phpCode ('?>'! EOF!|EOF!)
    ;

phpCode	
    :   namespaceSemicolon+ 
    |   namespaceBracket+ 
    |   withoutNamespace
    ;

namespaceSemicolon
    :   'namespace' namespaceId namespaceBody=';' statement*
        -> ^('namespace'
            TYPE_NAME[$namespaceId.start,BACKSLASH + $namespaceId.text +BACKSLASH]
            ^(NAMESPACE_BODY[$namespaceBody,"nBody"] statement*)
        )
    ;

namespaceBracket
    :   'namespace' namespaceIdOrEmpty  namespaceBody='{' statement* '}'
        -> ^('namespace'
            namespaceIdOrEmpty
            ^(NAMESPACE_BODY[$namespaceBody,"nBody"] statement*)
        )
    ;
namespaceIdOrEmpty
    :   namespaceId -> TYPE_NAME[$namespaceId.start, BACKSLASH + $namespaceId.text + BACKSLASH]
    |   /* empty */ -> DEFAULT_NAMESPACE[$namespaceIdOrEmpty.start, BACKSLASH]
    ;

//Must be before Identifier otherwise Identifier matches true and false
Bool    
    :   'true'|'false'
    ;

namespaceId
    :   Identifier ('\\' Identifier)* -> Identifier+
    ;

Identifier    
    :   ('a'..'z'|'A'..'Z'|'_'|'\u007f'..'\u00ff') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'\u007f'..'\u00ff')*
    ;

withoutNamespace 
    :   (statement*)
        -> ^(Namespace[$statement.start,"namespace"]
            DEFAULT_NAMESPACE[$statement.start, BACKSLASH]
            ^(NAMESPACE_BODY[$statement.start,"nBody"] statement*)
        )
    ;

statement    
    :   useDefinitionList
    |   definition
    |   instruction
    ;
    
useDefinitionList
    :   'use' firstUseDeclaration=useDefinition (',' otherDeclaration=useDefinition)* ';'
        -> ^('use' ^(USE_DECLARATION[$firstUseDeclaration.start, "uDecl"] useDefinition) (^(USE_DECLARATION[$otherDeclaration.start, "uDecl"] useDefinition))* )
    ;
    
useDefinition
    :   usingType 'as' Identifier
        -> usingType Identifier
    
    |   type=Identifier 'as' alias=Identifier
        -> TYPE_NAME[$type, $type.text] $alias

    |   usingType
        -> usingType Identifier[$usingType.start, $usingType.text.substring($usingType.text.lastIndexOf(BACKSLASH)+1)]
    ;
    
usingType
    :   Identifier '\\' namespaceId -> TYPE_NAME[$usingType.start, $usingType.text]
    |   '\\' namespaceId -> TYPE_NAME[$usingType.start, $usingType.text]
    ;
    
definition
    :   functionDefinition
    |   constDefinitionList
    ;

constDefinitionList
    :   begin='const' constAssign=constantAssignment (',' constantAssignment)* ';'
        -> ^(CONSTANT_DECLARATION_LIST[$begin, "consts"]
            ^(TYPE[$constAssign.start,"type"]
                ^(TYPE_MODIFIER[$constAssign.start,"tMod"]
                    Public[$constAssign.start,"public"]
                    Static[$constAssign.start,"static"]
                    Final[$constAssign.start,"final"]
                )
                QuestionMark[$constAssign.start, "?"]
            )
            constantAssignment+
        )
    ;
    
constantAssignment
    :   id=Identifier '=' unaryPrimitiveAtom
        ->^(Identifier[$id, $id.text+"#"] unaryPrimitiveAtom)
    ;


unaryPrimitiveAtom
    :   uplus = '+' primitiveAtomWithConstant  -> ^(UNARY_PLUS[$uplus, "uPlus"] primitiveAtomWithConstant)
    |   uminus = '-' primitiveAtomWithConstant -> ^(UNARY_MINUS[$uminus,"uMinus"] primitiveAtomWithConstant)
    |   primitiveAtomWithConstant
    ;

functionDefinition    
    :   func='function' id=methodIdentifier formalParameters block='{' instruction* '}'
        -> ^($func
            FUNCTION_MODIFIER[$func,"fMod"]
            ^(TYPE[$id.start,"type"] TYPE_MODIFIER[$id.start,"tMod"] QuestionMark[$id.start, "?"])
            methodIdentifier
            formalParameters
            ^(BLOCK[$block,"block"] instruction*)
        )
    ;

methodIdentifier
    :   id=Identifier -> Identifier[$id,$id.text+"()"]
    ;
    
classInterfaceTypeWithoutMixed
    :   root='\\' namespaceId -> TYPE_NAME[$root, BACKSLASH + $namespaceId.text]
    |   namespaceId           -> TYPE_NAME[$namespaceId.start, $namespaceId.text]
    ;

formalParameters
    :   params='(' paramList? ')' -> ^(PARAMETER_LIST[$params,"params"] paramList?)
    ;

paramList
    :   paramDeclaration (','! paramDeclaration)*
    ;
    
paramDeclaration
    :   parameterType VariableId ('=' unaryPrimitiveAtom)?
        -> ^(PARAMETER_DECLARATION[$paramDeclaration.start,"pDecl"] parameterType ^(VariableId unaryPrimitiveAtom?))
    ;
    
parameterType
    :   a=TypeArray
        -> ^(TYPE[$a, "type"] TYPE_MODIFIER[$a, "tMod"] $a)

    |   t=classInterfaceTypeWithoutMixed
        -> ^(TYPE[$t.start, "type"] TYPE_MODIFIER[$t.start, "tMod"] $t)

    |   /* empty */
        -> ^(TYPE[$parameterType.start, "type"]
            TYPE_MODIFIER[$parameterType.start, "tMod"]
            QuestionMark[$parameterType.start, "?"]
        )
    ;   

VariableId    
    :   '$' Identifier
    ;

instruction
    :   ifCondition
    |   switchCondition
    |   forLoop
    |   foreachLoop
    |   whileLoop
    |   doWhileLoop
    |   tryCatch
    |   expression ';' -> ^(EXPRESSION[$expression.start,"expr"] expression)
    |   expr=';' -> EXPRESSION[$expr,"expr"]
    |   'return'^ expression? ';'!
    |   'throw'^ expression ';'!
    |   'echo'^ expressionList ';'!
    |   'break'^ Int? ';'!
    |   'continue'^ Int? ';'!
    |   block='{''}' -> EXPRESSION[$block,"expr"]
    |   '{'! instruction+ '}'!
    ;

ifCondition
    :   'if' '(' expression ')' instructionThen=instruction ( 'else' instructionElse =instruction )?
        -> ^('if'
            expression
            ^(BLOCK_CONDITIONAL[$instructionThen.start,"cBlock"] $instructionThen)
            ^(BLOCK_CONDITIONAL[$instructionElse.start,"cBlock"] $instructionElse)?
        )
    ;    
   
switchCondition
    :   'switch' '(' expression ')' '{' switchContent? '}'  -> ^('switch' expression switchContent?)
    ;

switchContent
    :
    (    label=caseLabel+ 
         (   defaultLabel 
             (   caseLabel+
                 (   instr=instruction+ content=switchContentWithoutDefault?
                 |   /* empty */
                 )
             |   instr=instruction+ content=switchContentWithoutDefault?
             |   /* empty */
             )                 
         |   instr=instruction+ content=switchContent?             
         |   /* empty */
         )
         
    |    label=defaultLabel 
         (   caseLabel+
             (   instr=instruction+ content=switchContentWithoutDefault?
             |   /* empty */
             )
         |   instr=instruction+ content=switchContentWithoutDefault?
         |    /* empty */
         )
    )
    ->  ^(SWITCH_CASES[$label.start,"cases"] caseLabel* defaultLabel?)
        ^(BLOCK_CONDITIONAL[$instr.start,"cBlock"] instruction*)
        $content?
    ;

switchContentWithoutDefault
    :   caseLabel+
        (   instruction+ content=switchContentWithoutDefault?
        |   /* empty */
        )
        ->  ^(SWITCH_CASES[$caseLabel.start,"cases"] caseLabel+)
            ^(BLOCK_CONDITIONAL[$instruction.start,"cBlock"] instruction*)
            $content?
    ;

caseLabel
    :   'case'! expression ':'!
    ;

defaultLabel
    :   'default' ':'!
    ;

forLoop
    :   'for' forInit forCondition forUpdate instruction
        -> ^('for'
            forInit
            forCondition
            forUpdate
            ^(BLOCK_CONDITIONAL[$instruction.start, "cBlock"] instruction)
        )
    ;

forInit
    :   init='(' expressionList? -> ^(EXPRESSION_LIST[$init, "exprs"] expressionList?)
    ;

forCondition
    :   condition=';' expressionList? -> ^(EXPRESSION_LIST[$condition, "exprs"] expressionList?)
    ;

forUpdate
    :   update=';' expressionList? ')' -> ^(EXPRESSION_LIST[$update, "exprs"] expressionList?)
    ;

foreachLoop
    :   'foreach' '(' expression 'as' (keyVarId=VariableId '=>')? valueVarId=VariableId ')' instruction
        -> ^('foreach' 
            expression 
            $keyVarId? 
            $valueVarId 
            ^(BLOCK_CONDITIONAL[$instruction.start, "cBlock"] instruction)
        )
    ;

whileLoop
    :   'while' '(' expression ')' instruction
        -> ^('while' expression ^(BLOCK_CONDITIONAL[$instruction.start, "cBlock"] instruction))
    ;

doWhileLoop
    :   'do' instruction 'while' '(' expression ')' ';'
        -> ^('do' ^(BLOCK[$instruction.start, "block"] instruction) expression)
    ;

tryCatch
    :   'try' tryBegin='{' instruction* '}' catchBlock+
        -> ^('try' ^(BLOCK_CONDITIONAL[$instruction.start,"cBlock"] instruction*) catchBlock+)
    ;

catchBlock
    :   catchBegin='catch' '(' classInterfaceTypeWithoutMixed VariableId ')' block='{' instruction* '}'
        -> ^($catchBegin
            classInterfaceTypeWithoutMixed
            VariableId
            ^(BLOCK_CONDITIONAL[$instruction.start,"cBlock"] instruction*)
        )
    ;

expression
    :   logicOrWeak 
    ;

logicOrWeak
    :   logicXorWeak ('or'^ logicXorWeak)*
    ; 

logicXorWeak
    :   logicAndWeak ('xor'^ logicAndWeak)*
    ; 

logicAndWeak
    :   assignment ('and'^ assignment)*
    ;
   
assignment
    :   ternary
        (
            (   '='
            |   '+='
            |   '-='
            |   '*='
            |   '/='
            |   '&='
            |   '|='
            |   '^='
            |   '%='
            |   '.='
            |   '<<='
            |   '>>='
            )^
            assignment
        )?
    ;

ternary
    :   logicOr ('?'^ expression ':'! logicOr)*
    ;

logicOr
    :   logicAnd ('||'^ logicAnd)*
    ;

logicAnd
    :   bitwiseOr ('&&'^ bitwiseOr)*
    ;

bitwiseOr
    :   bitwiseXor ('|'^ bitwiseXor)*
    ;

bitwiseXor
    :   bitwiseAnd ('^'^ bitwiseAnd)*
    ;

bitwiseAnd
    :   equality ('&'^ equality)*
    ;
    
equality
    :   comparison (equalityOperator^ comparison)?
    ;

equalityOperator
    :   '=='
    |   '==='
    |   '!='
    |   o='<>' -> NotEqual[$o,"!="]
    |   '!=='
    ;
    
comparison
    :   bitwiseShift ( ('<'|'<='|'>'|'>=')^ bitwiseShift)?
    ;
     
bitwiseShift
    :   termOrStringConcatenation (('<<'|'>>')^ termOrStringConcatenation)*
    ;

termOrStringConcatenation
    :   factor (('+'|'-'|'.')^ factor)*
    ;

factor
    :   instanceOf (('*'|'/'|'%')^ instanceOf)*
    ;

instanceOf
    :   unary ('instanceof'^ (classInterfaceTypeWithoutMixed|VariableId))?
    ;

unary
    :   cast='(' scalarTypesInclArrayWithModifier ')' unary -> ^(CAST[$cast,"casting"] scalarTypesInclArrayWithModifier unary)
    |   plus='++' postFixVariableWithoutCallAtTheEnd  -> ^(PRE_INCREMENT[$plus,"preIncr"] postFixVariableWithoutCallAtTheEnd)
    |   minus='--' postFixVariableWithoutCallAtTheEnd -> ^(PRE_DECREMENT[$minus,"preDecr"] postFixVariableWithoutCallAtTheEnd)
    |   ('@'|'~'|'!')^ unary
    |   uplus = '+' unary -> ^(UNARY_PLUS[uplus,"uPlus"] unary)
    |   uminus = '-' unary -> ^(UNARY_MINUS[$uminus,"uMinus"] unary)
    |   cloneOrNew
    ;
    
scalarTypesInclArrayWithModifier
    :   (    t='bool'
        |    t='boolean' {t.setType(Bool); t.setText("bool");}
        |    t='int'
        |    t='integer' {t.setType(Int); t.setText("int");}
        |    t='float'
        |    t='double'  {t.setType(Float); t.setText("float");}
        |    t='real'    {t.setType(Float); t.setText("float");}
        |    t='string'
        |    t='array'
        )
        -> ^(TYPE[$t,"type"] TYPE_MODIFIER[$t,"tMod"] $t)
    ;
   
cloneOrNew
        //reference back to unary necessary, since clone unary $b; is valid e.g. clone @$b;
    :   'clone'^ unary
//TODO rstoll TINS-108 - class, TINS-109 - interface
//    |   newObject
    |   primary
    ;

primary
    :   'exit'^ ('('! expression ')'!)?
//
    |   postFixCall
    |   (VariableId -> VariableId)
        (    (//TODO rstoll TINS-108 - class, TINS-109 - interface
             //(call* -> ^(METHOD_CALL_POSTFIX[$call.start,"mpCall"] VariableId call*) )
             //(    fieldAccess = '->' Identifier -> ^(FIELD_ACCESS[$fieldAccess,"fieAccess"] $postFixVariableWithoutCallAtTheEnd Identifie
             /*|*/    arrayAccess = '[' expression ']'
                     -> ^(ARRAY_ACCESS[$arrayAccess,"arrAccess"] $primary expression)
             //)
             )*
             (    o='++' -> ^(POST_INCREMENT[$o, "postIncr"] $primary)
             |    o='--' -> ^(POST_DECREMENT[$o, "postDecr"] $primary)
             )?
        )
    |   atom
    ;

postFixCall
    :   (    functionCall -> functionCall
        //|    methodCall -> methodCall
        //|    methodCallSelfOrParent -> methodCallSelfOrParent
        //|    methodCallStatic -> methodCallStatic
        )
        (//TODO rstoll TINS-108 - class, TINS-109 - interface
        /*    fieldAccess = '->' Identifier -> ^(FIELD_ACCESS[$fieldAccess,"fieAccess"] $postFixCall Identifier)
        |*/    arrayAccess = '[' expression ']' -> ^(ARRAY_ACCESS[$arrayAccess,"arrAccess"] $postFixCall expression)
        //|    call -> ^(METHOD_CALL_POSTFIX[$call.start,"mpCall"] $postFixCall call)
        )*
    ;
       
functionCall
    :   functionIdentifier actualParameters
        -> ^(FUNCTION_CALL[$functionIdentifier.start,"fCall"] functionIdentifier actualParameters)
    ;
   
functionIdentifier
    :   classInterfaceTypeWithoutMixed
        -> TYPE_NAME[$classInterfaceTypeWithoutMixed.start,$classInterfaceTypeWithoutMixed.text+"()"]
    ;

actualParameters
    :   list='(' expressionList? ')' -> ^(ACTUAL_PARAMETERS[$list,"args"] expressionList?)
    ;
   
expressionList
    :   expression (','! expression)*
    ;

postFixVariableWithoutCallAtTheEnd
    :   (VariableId -> VariableId)
        (
             //TODO rstoll TINS-108 - class, TINS-109 - interface
             //(call* -> ^(METHOD_CALL_POSTFIX[$call.start,"mpCall"] VariableId call*) )
             //(    fieldAccess = '->' Identifier -> ^(FIELD_ACCESS[$fieldAccess,"fieAccess"] $postFixVariableWithoutCallAtTheEnd Identifie
             /*|*/    arrayAccess = '[' expression ']'
                     -> ^(ARRAY_ACCESS[$arrayAccess,"arrAccess"] $postFixVariableWithoutCallAtTheEnd expression)
             //)
        )*
    ;    

atom    
    :   primitiveAtomWithConstant
    |   '(' expression ')' -> expression
    ;   

primitiveAtomWithConstant
    :   Bool
    |   Int
    |   Float
    |   String
    |   'null'
    |   array
    |   globalConstant
    ;

globalConstant
    :   identifier=classInterfaceTypeWithoutMixed -> CONSTANT[$identifier.start, $identifier.text+"#"]
    ;     
    
array    
    :   arr='[' arrayContent? ']'  -> ^(TypeArray[$arr,"array"] arrayContent?)
    |   arr='array' '(' arrayContent? ')' -> ^($arr arrayContent?)
    ;
    
arrayContent
    :   arrayKeyValue (','! arrayKeyValue)*
    ;
    
arrayKeyValue
    :   key=expression 
        (    /* empty */ -> $key
        |    ('=>' value=expression) -> ^('=>' $key $value)
        )
    ;

Int     
    :   DECIMAL
    |   HEXADECIMAL
    |   OCTAL
    |   BINARY
    ;

fragment
DECIMAL
    :   ('1'..'9') ('0'..'9')*
    |   '0'
    ;
        
fragment          
HEXADECIMAL 
    :   '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+
    ;

fragment
OCTAL    
    :   '0' ('0'..'7')+
    ;

fragment
BINARY    
    :   '0b'('0'|'1')+
    ;

Float
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;
    
fragment
EXPONENT 
    :   ('e'|'E') ('+'|'-')? ('0'..'9')+ 
    ;
    
String    
    :   STRING_SINGLE_QUOTED 
    |   STRING_DOUBLE_QUOTED 
    ;

fragment
STRING_SINGLE_QUOTED
    :   '\'' 
        (    ('\\\\')=>'\\\\' 
        |    ('\\\'')=>'\\\'' 
        |    ~('\'')
        )* 
        '\''
    ;
    
fragment
STRING_DOUBLE_QUOTED
    :   '"' 
        (    ('\\\\') => '\\\\'
        |    ('\\"') => '\\"'
        |    ('\\$') => '\\$'
        |    ~('"' | '$')
        )*
        '"'
    ;

Comment
    :   ('//'|'#') ~('\n'|'\r')* '\r'? '\n' {$channel = HIDDEN;}
        //comment could be at the end of the file and thus no \n needed
    |   ('//'|'#') ~('\n'|'\r')* {$channel = HIDDEN;}
    |   '/*' ( options {greedy = false;} : . )* '*/' {$channel = HIDDEN;}
    ;

Whitespace
    :   (    ' '
        |    '\t'
        |    '\r'
        |    '\n'
        )
        {$channel = HIDDEN;}
    ;