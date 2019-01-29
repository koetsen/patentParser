package org.koet;

public class CssLocator {

    public static final String TITLE_CSS() {
        return "h1#title";
    }

    public static final String ABSTRACT_CSS() {

        return "patent-text[name=\"abstract\"]";
    }

    public static final String ABSTRACT_CSS_DIV() {
        return "div.abstract.style-scope.patent-text";
    }

    public static final String CPC_CSS() {
        return "section#classifications";
    }

    public static final String CPC_DIVS_CSS() {

        return "div.style-scope.classification-tree";
    }

    public static final String CPC_Class_CSS() {

        return "state-modifier.code.style-scope.classification-tree";
    }

    public static final String CPC_DESCRIPTION_CSS() {

        return "span.description.style-scope.classification-tree";
    }

    public static final String DESCRIPTION_BODY_CSS() {
        return "section#description";
    }

    public static final String INVENTION_TITLE_CSS() {
        return "invention-title";
    }

    public static final String DESCRIPTION_TEXT_CSS() {
        return "p.style-scope.patent-text[num]";
    }

    public static final String DESCRIPTION_TEXT_ALT_CSS() {
        return "div.description-line.style-scope.patent-text[num]";
    }

    public static final String KNOWLEDGE_CARD_CSS() {
        return "section.knowledge-card.style-scope.patent-result";
    }

    public static final String IVENTOR_CSS() {
        return "state-modifier[data-inventor]";
    }
}

