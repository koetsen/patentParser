package org.koet;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class ParsePatent {

    private Document doc;
    private Element knowledgeCard;

    public ParsePatent(File fileName) {
        try {
            this.doc = Jsoup.parse(fileName, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Element> knowledgeArr = this.doc.select(CssLocator.KNOWLEDGE_CARD_CSS());
        this.knowledgeCard = knowledgeArr.get(0);

        if (this.knowledgeCard == null) {
            System.err.printf("No knowledge card was found for %s", fileName.toString());
        }

        if (knowledgeArr != null && knowledgeArr.size() > 1) {
            System.err.printf("No knowledge card was found for %s", fileName.toString());
        }
    }

    public String getTitle() {
        Elements elem = this.doc.select(CssLocator.TITLE_CSS());
        elem.select("overlay-tooltip").remove();
        return elem.text();
    }

    public String getAbstract() {
        Elements elem = this.doc.select(CssLocator.ABSTRACT_CSS());
        return elem.select(CssLocator.ABSTRACT_CSS_DIV()).text();
    }

    public Map<String, String> getCPC() {

        Map<String, String> cpcClassesMap = new LinkedHashMap<String, String>();

        Elements cpcElements = this.doc.select(CssLocator.CPC_CSS()).select(CssLocator.CPC_DIVS_CSS());
        for (Element cpcElem : cpcElements) {
            String cpcClass = cpcElem.select(CssLocator.CPC_Class_CSS()).attr("data-cpc");
            String cpcDescription = cpcElem.select(CssLocator.CPC_DESCRIPTION_CSS()).text();

            if (cpcClass.isEmpty() || cpcDescription.isEmpty()) {
                continue;
            }

            if (cpcClassesMap.containsKey(cpcClass)) {
                continue;
            }
            cpcClassesMap.put(cpcClass, cpcDescription);
        }
        return cpcClassesMap;
    }

    public String getInventionTitle() {
        // INID Code (54)
        return this.doc.select(CssLocator.INVENTION_TITLE_CSS()).text();
    }

    public ArrayList<String> getInventors() {

        ArrayList<String> inventors = new ArrayList<>();
        for (Element elm : this.knowledgeCard.select(CssLocator.IVENTOR_CSS())) {
            inventors.add(elm.attr("data-inventor"));
        }
        return inventors;
    }

    public ArrayList<String> getDescription() {

        // der erste CSS-Locator trifft nicht alle Elemente
        Elements descriptionText = this.doc.select(CssLocator.DESCRIPTION_BODY_CSS())
                .select(CssLocator.DESCRIPTION_TEXT_CSS());

        if (descriptionText.size() == 0) {
            descriptionText = this.doc.select(CssLocator.DESCRIPTION_BODY_CSS())
                    .select(CssLocator.DESCRIPTION_TEXT_ALT_CSS());
        }

        ArrayList<String> returnList = new ArrayList<>();
        for (Element elem : descriptionText) {
            if (elem.text().isEmpty()) {
                continue;
            }
            returnList.add(cleanString(elem.text(), getAllRegex()));
        }
        return returnList;
    }

    private String cleanString(String input, List<Pattern> pattern) {

        String returnString = input;
        for (Pattern regex : pattern) {
            returnString = regex.matcher(returnString).replaceAll("");
        }
        return returnString;
    }

    private ArrayList<Pattern> getAllRegex() {

        // falls es mal mehr als ein regex geben sollte
        ArrayList<Pattern> regexList = new ArrayList<>();
        regexList.add(RegexPattern.PATTERN_BRACET_NUMBER());

        return regexList;
    }

}
