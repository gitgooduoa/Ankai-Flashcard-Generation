import dev.apollointhehouse.API
import dev.apollointhehouse.models.Note
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test

class APITest {
    val exampleText = """
        ## 🧬 Biological Evolution of Apes — Markdown Notes

        ### 1. Overview
        - **Apes** belong to the superfamily **Hominoidea**.
        - Includes:
          - **Lesser apes** → gibbons
          - **Great apes** → orangutans, gorillas, chimpanzees, and humans
        - Distinguished from monkeys by:
          - No tail  
          - Larger brains  
          - More flexible shoulder joints  

        ---

        ### 2. Evolutionary Origins
        - Apes evolved from early **catarrhine primates** (Old World monkeys + apes).
        - Timeframe:
          - ~25–30 million years ago (Miocene epoch)
        - Early ape-like ancestors:
          - *Proconsul* (tree-dwelling, monkey-like body, ape-like teeth)

        ---

        ### 3. Key Evolutionary Traits
        - **Increased brain size**
          - Especially in great apes and humans
        - **Enhanced vision**
          - Forward-facing eyes → depth perception
        - **Locomotion adaptations**
          - Brachiation (swinging) in gibbons
          - Knuckle-walking in gorillas/chimpanzees
          - Bipedalism in humans
        - **Reduced snout & reliance on smell**
        - **Complex social behavior**

        ---

        ### 4. Divergence of Ape Lineages
        #### Timeline (approximate):
        - ~18–20 MYA: Lesser apes split from great apes  
        - ~14 MYA: Orangutans diverge  
        - ~8–10 MYA: Gorillas diverge  
        - ~6–7 MYA: Humans and chimpanzees split  

        ---

        ### 5. Major Groups of Apes
        #### Lesser Apes (Family: Hylobatidae)
        - Gibbons and siamangs  
        - Small-bodied, highly arboreal  
        - Specialized for brachiation  

        #### Great Apes (Family: Hominidae)
        - **Orangutans** (Asia)
        - **Gorillas** (Africa)
        - **Chimpanzees & bonobos** (Africa)
        - **Humans**

        ---

        ### 6. Human Evolution (Hominins)
        - Humans are part of the ape lineage
        - Key stages:
          - *Australopithecus* → early bipedalism  
          - *Homo habilis* → tool use  
          - *Homo erectus* → migration out of Africa  
          - *Homo sapiens* → advanced cognition & culture  

        ---

        ### 7. Environmental Influences
        - Climate changes in the Miocene:
          - Forest fragmentation → adaptation to diverse habitats
        - Shift from dense forests → savannas influenced:
          - Bipedalism in human ancestors  
          - Dietary changes  

        ---

        ### 8. Genetic Evidence
        - Humans share:
          - ~98–99% DNA with chimpanzees  
        - Molecular clocks help estimate divergence times  
        - Fossil + genetic data together refine evolutionary models  

        ---

        ### 9. Importance of Ape Evolution
        - Helps explain:
          - Human origins  
          - Development of intelligence and social systems  
        - Provides insight into:
          - Adaptation  
          - Speciation  
          - Primate conservation  

        ---

        ### 10. Quick Summary
        - Apes evolved in Africa during the Miocene  
        - Diversified into multiple lineages  
        - Humans are one branch of the great apes  
        - Evolution shaped by environment, behavior, and genetics  
    """.trimIndent()

    private val logger = KotlinLogging.logger {}

    @Test
    fun `test generating flashcards from note content`() = runTest {
        val flashcards = API.genRequest(Note(title = "Evoluton of Apes", content = exampleText))
        logger.info { "Response: ${Json.encodeToString(flashcards)}" }
        assert(flashcards.isNotEmpty())
    }

}