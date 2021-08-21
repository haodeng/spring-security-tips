package demo.hao;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/items")
public class ItemController {
    private static Map<Integer, Item> itemsMap = new HashMap<>();
    static {
        itemsMap.put(1, new Item(1, "item1"));
        itemsMap.put(2, new Item(2, "item2"));
        itemsMap.put(3, new Item(3, "item3"));
        itemsMap.put(4, new Item(4, "item4"));
    }

    @GetMapping
    CollectionModel<EntityModel<Item>> findAll(Authentication auth) {
        ItemController controller = methodOn(ItemController.class);

        Link selfLink = linkTo(controller.findAll(auth)).withSelfRel();
        Links allLinks = Links.of(selfLink);

        Collection<Item> items = itemsMap.values();
        List<EntityModel<Item>> itemsList = StreamSupport.stream(items.spliterator(), false)
                .map(item -> findOne(item.getId(), auth))
                .collect(Collectors.toList());

        return CollectionModel.of(itemsList, allLinks);
    }

    @GetMapping("/{id}")
    public EntityModel<Item> findOne(@PathVariable Integer id, Authentication auth) {
        ItemController controller = methodOn(ItemController.class);

        Link selfLink = linkTo(controller.findOne(id, auth)).withSelfRel();
        Links allLinks;
        if (auth.getAuthorities().contains(SecurityConfig.ROLE_ADMIN)) {
            Link deleteLink = linkTo(controller.deleteItem(id)).withRel("delete");
            allLinks = Links.of(selfLink, deleteLink);
        }
        else
        {
            allLinks = Links.of(selfLink);
        }

        return EntityModel.of(itemsMap.get(id), allLinks);
    }

    @PreAuthorize("hasRole('" + SecurityConfig.ADMIN + "')")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteItem(@PathVariable Integer id) {
        itemsMap.remove(id);
        return ResponseEntity.noContent().build();
    }
}
