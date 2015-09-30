package ru.atott.combiq.web.controller;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.atott.combiq.service.bean.Question;
import ru.atott.combiq.service.question.impl.SearchContext;
import ru.atott.combiq.service.question.impl.SearchResponse;
import ru.atott.combiq.service.question.GetQuestionService;
import ru.atott.combiq.web.bean.PagingBean;
import ru.atott.combiq.web.bean.PagingBeanBuilder;
import ru.atott.combiq.web.utils.GetQuestionContextBuilder;
import ru.atott.combiq.web.view.SearchViewBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController extends BaseController {
    private PagingBeanBuilder pagingBeanBuilder = new PagingBeanBuilder();
    @Autowired
    private GetQuestionService getQuestionService;
    @Autowired
    private GetQuestionContextBuilder getQuestionContextBuilder;

    @RequestMapping(value = "/questions/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(value = "q", defaultValue = "") String dsl) {
        page = getZeroBasedPage(page);
        SearchContext context = getQuestionContextBuilder.listByDsl(page, dsl);
        return getView(request, context, dsl);
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ModelAndView questions(HttpServletRequest request,
                                  @RequestParam(defaultValue = "1") int page) {
        page = getZeroBasedPage(page);
        SearchContext context = getQuestionContextBuilder.list(page);
        return getView(request, context, null, "Вопросы", true);
    }

    @RequestMapping(value = "/questions/tagged/{tags}")
    public ModelAndView taggedQuestions(HttpServletRequest request,
                                        @RequestParam(defaultValue = "1") int page,
                                        @PathVariable("tags") String tags) {
        page = getZeroBasedPage(page);
        ArrayList<String> tagsList = Lists.newArrayList(StringUtils.split(tags, ','));
        SearchContext context = getQuestionContextBuilder.listByTags(page, tagsList);
        return getView(request, context, null);
    }

    @RequestMapping(value = "/questions/level/{level}")
    public ModelAndView level(HttpServletRequest request,
                              @RequestParam(defaultValue = "1") int page,
                              @PathVariable("level") String level) {
        page = getZeroBasedPage(page);
        SearchContext context = getQuestionContextBuilder.listByLevel(page, level);
        return getView(request, context, null);
    }

    private ModelAndView getView(HttpServletRequest request, SearchContext context, String dsl,
                                 String subTitle, boolean questionsCatalog) {
        SearchResponse questionsResponse = getQuestionService.getQuestions(context);

        PagingBean paging = pagingBeanBuilder.build(questionsResponse.getQuestions(), questionsResponse.getQuestions().getNumber(), request);
        List<Question> questions = questionsResponse.getQuestions().getContent();
        dsl = StringUtils.defaultIfBlank(dsl, context.getDslQuery().toDsl());

        SearchViewBuilder viewBuilder = new SearchViewBuilder();
        viewBuilder.setQuestions(questions);
        viewBuilder.setPaging(paging);
        viewBuilder.setDsl(dsl);
        viewBuilder.setPopularTags(questionsResponse.getPopularTags());
        viewBuilder.setSubTitle(subTitle);
        viewBuilder.setQuestionsCatalog(questionsCatalog);
        return viewBuilder.build();
    }

    private ModelAndView getView(HttpServletRequest request, SearchContext context, String dsl) {
        return getView(request, context, dsl, null, false);
    }
}
