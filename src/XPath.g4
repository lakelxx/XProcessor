grammar XPath;

ap          :    DOC '(' FILENAME ')''/'rp                           # ap1
            |    DOC '(' FILENAME ')''//'rp                          # ap2
            ;

rp          :    NAME                                                # rpTag
            |    '*'                                                 # rpStar
            |    '.'                                                 # rpDot1
            |    '..'                                                # rpDot2
            |    'text()'                                            # rpText
            |    '@' NAME                                            # rpAttr
            |    '(' rp ')'                                          # rpParens
            |    rp '/' rp                                           # rpSlash1
            |    rp '//' rp                                          # rpSlash2
            |    rp '[' filter ']'                                   # rpFilter
            |    rp ',' rp                                           # rpComma
            ;

filter      :    rp                                                  # ftRp
            |    rp '=' rp                                           # ftEq
            |    rp 'eq' rp                                          # ftEq
            |    rp '==' rp                                          # ftIs
            |    rp 'is' rp                                          # ftIs
            |    '(' filter ')'                                      # ftParens
            |    filter 'and' filter                                 # ftAnd
            |    filter 'or' filter                                  # ftOr
            |    'not' filter                                        # ftNot
            ;

DOC         :    'doc'
            |    'document'
            ;
FILENAME    :    '"' NAME '"';
NAME        :    [a-zA-Z0-9._]+ ;
WS          :    [ \t\r\n]+ -> skip;