grammar XQuery;
import XPath;

xq          :    var                                                 # xqVar
            |    sConstant                                           # xqSc
            |    ap                                                  # xqAp
            |    '(' xq ')'                                          # xqParens
            |    xq ',' xq                                           # xqComma
            |    xq '/' rp                                           # xqSlash1
            |    xq '//' rp                                          # xqSlash2
            |    '<' NAME '>' '{' xq '}' '<''/'NAME'>'               # xqTag
            |    forClause letClause? whereClause? returnClause      # xqFLWR
            |    letClause xq                                        # xqLet
            ;

forClause   :    'for' var 'in' xq (',' var 'in' xq)*;

letClause   :    'let' var ':=' xq (',' var ':=' xq)*;

whereClause :    'where' cond;

returnClause:    'return' xq;

cond        :    xq '=' xq                                           # condEq
            |    xq 'eq' xq                                          # condEq
            |    xq '==' xq                                          # condIs
            |    xq 'is' xq                                          # condIs
            |    'empty' '(' xq ')'                                  # condEmpty
            |    'some' var 'in' xq (var 'in' xq)* 'satisfies' cond  # condSatis
            |    '(' cond ')'                                        # condParens
            |    cond AND cond                                       # condAnd
            |    cond OR  cond                                       # condOr
            |    NOT cond                                            # condNot
            ;

var         :    '$' NAME;