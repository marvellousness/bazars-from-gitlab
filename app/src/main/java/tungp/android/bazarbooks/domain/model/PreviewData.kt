package tungp.android.bazarbooks.domain.model

import java.util.UUID

object PreviewData {
    val topOfWeek = listOf(
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "The Bane Chronicles",
            author = "Maureen Johnson",
            cover = "https://covers.openlibrary.org/b/id/13151170-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Koralina",
            author = "Neil Gaiman",
            cover = "https://covers.openlibrary.org/b/id/12634691-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Red, White & Royal Blue",
            author = "Casey McQuiston",
            cover = "https://covers.openlibrary.org/b/id/9171544-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Twisted Lies",
            author = "Ana Huang",
            cover = "https://covers.openlibrary.org/b/id/12816871-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Fourth Wing",
            author = "Rebecca Yarros",
            cover = "https://covers.openlibrary.org/b/id/14407898-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99
        )
    )
    val popular = listOf(
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Fifty Shades of Grey",
            author = "E. L. James",
            cover = "https://covers.openlibrary.org/b/id/10740111-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "The River Devil",
            author = "Diane Whiteside",
            cover = "https://covers.openlibrary.org/b/id/505653-M.jpg",
            price = 14.99
        ), Book(
            isbn = UUID.randomUUID().toString(),
            title = "Fifty Shades of Grey",
            author = "E. L. James",
            cover = "https://covers.openlibrary.org/b/id/10740111-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "The River Devil",
            author = "Diane Whiteside",
            cover = "https://covers.openlibrary.org/b/id/505653-M.jpg",
            price = 14.99
        )
    )

    val favoriteBooksByUser = listOf(
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "The River Devil",
            author = "Diane Whiteside",
            cover = "https://covers.openlibrary.org/b/id/505653-M.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Koralina",
            author = "Neil Gaiman",
            cover = "https://covers.openlibrary.org/b/id/12634691-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Fifty Shades of Grey",
            author = "E. L. James",
            cover = "https://covers.openlibrary.org/b/id/10740111-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99
        ),
        Book(
            isbn = UUID.randomUUID().toString(),
            title = "Fourth Wing",
            author = "Rebecca Yarros",
            cover = "https://covers.openlibrary.org/b/id/14407898-L.jpg",
            price = 14.99
        ),
    )

    val authors = listOf(Author("1", "title", "cover", "authorName"))
    
    // List of vendor categories for filtering
    val categories = listOf("All", "Books", "Poems", "Stationery", "Magazines")
    
    val vendors = listOf(
        Vendor("1", "Oxford Bookstore", "https://covers.openlibrary.org/b/id/12634691-L.jpg", "John Oxford", 45, 4, "Books"),
        Vendor("2", "Poems & Co", "https://covers.openlibrary.org/b/id/14416194-L.jpg", "Emily Dickinson", 28, 5, "Poems"),
        Vendor("3", "Paper Trail", "https://covers.openlibrary.org/b/id/9171544-L.jpg", "Mark Stationers", 62, 3, "Stationery"),
        Vendor("4", "Literary Haven", "https://covers.openlibrary.org/b/id/12816871-L.jpg", "Sarah Bookworm", 53, 4, "Books"),
        Vendor("5", "Verse Universe", "https://covers.openlibrary.org/b/id/14407898-L.jpg", "Robert Frost", 31, 5, "Poems"),
        Vendor("6", "Magazine Rack", "https://covers.openlibrary.org/b/id/471953-L.jpg", "James Editor", 40, 3, "Magazines")
    )
}