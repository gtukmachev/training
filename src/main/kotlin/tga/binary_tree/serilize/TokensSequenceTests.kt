package tga.binary_tree.serilize

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TokensSequenceTests {

    @Test fun empty() {
        assertThat( TokensSequence("").toList() ).isEqualTo( emptyList<Token>() )
    }


    @Test fun seqOpenBracket()  { assertThat( TokensSequence("[").toList() ).isEqualTo  ( listOf(OpenBracket)    )  }
    @Test fun seqCLoseBracket() { assertThat( TokensSequence("]").toList() ).isEqualTo  ( listOf(CloseBracket)   )  }
    @Test fun seqCommaToken()   { assertThat( TokensSequence(",").toList() ).isEqualTo  ( listOf(CommaToken)     )  }
    @Test fun seqValueToken1()  { assertThat( TokensSequence("1").toList() ).isEqualTo  ( listOf(ValueToken(1 )) )  }
    @Test fun seqValueTokenM1() { assertThat( TokensSequence("-1").toList() ).isEqualTo ( listOf(ValueToken(-1)) )  }

    @Test fun seqSmallTree() {
        assertThat( TokensSequence("1[2,3[4]]").toList() )
            .isEqualTo (
                listOf(
                    ValueToken(1),
                    OpenBracket,
                    ValueToken(2),
                    CommaToken,
                    ValueToken(3),
                    OpenBracket,
                    ValueToken(4),
                    CloseBracket,
                    CloseBracket,
                )
            )
    }

    @Test fun seqSmallTreeLongValues() {
        assertThat( TokensSequence("1123[232123,-323[-99999]]").toList() )
            .isEqualTo (
                listOf(
                    ValueToken(1123),
                    OpenBracket,
                    ValueToken(232123),
                    CommaToken,
                    ValueToken(-323),
                    OpenBracket,
                    ValueToken(-99999),
                    CloseBracket,
                    CloseBracket,
                )
            )
    }


}
