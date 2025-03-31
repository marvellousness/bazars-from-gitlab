package tungp.android.bazarbooks.domain.model

import java.util.UUID

object PreviewData {
    val topOfWeek = listOf(
        Book(
            bookId = "1",
            isbn = UUID.randomUUID().toString(),
            title = "The Bane Chronicles",
            author = "Maureen Johnson",
            cover = "https://covers.openlibrary.org/b/id/13151170-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "2",
            isbn = UUID.randomUUID().toString(),
            title = "Koralina",
            author = "Neil Gaiman",
            cover = "https://covers.openlibrary.org/b/id/12634691-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "3",
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "4",
            isbn = UUID.randomUUID().toString(),
            title = "Red, White & Royal Blue",
            author = "Casey McQuiston",
            cover = "https://covers.openlibrary.org/b/id/9171544-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "5",
            isbn = UUID.randomUUID().toString(),
            title = "Twisted Lies",
            author = "Ana Huang",
            cover = "https://covers.openlibrary.org/b/id/12816871-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "6",
            isbn = UUID.randomUUID().toString(),
            title = "Fourth Wing",
            author = "Rebecca Yarros",
            cover = "https://covers.openlibrary.org/b/id/14407898-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "7",
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99,
            rating = 4
        )
    )
    val popular = listOf(
        Book(
            bookId = "8",
            isbn = UUID.randomUUID().toString(),
            title = "Fifty Shades of Grey",
            author = "E. L. James",
            cover = "https://covers.openlibrary.org/b/id/10740111-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "9",
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "10",
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "11",
            isbn = UUID.randomUUID().toString(),
            title = "The River Devil",
            author = "Diane Whiteside",
            cover = "https://covers.openlibrary.org/b/id/505653-M.jpg",
            price = 14.99,
            rating = 4
        ), Book(
            bookId = "12",
            isbn = UUID.randomUUID().toString(),
            title = "Fifty Shades of Grey",
            author = "E. L. James",
            cover = "https://covers.openlibrary.org/b/id/10740111-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "13",
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "14",
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "15",
            isbn = UUID.randomUUID().toString(),
            title = "The River Devil",
            author = "Diane Whiteside",
            cover = "https://covers.openlibrary.org/b/id/505653-M.jpg",
            price = 14.99,
            rating = 4
        )
    )

    val favoriteBooksByUser = listOf(
        Book(
            bookId = "16",
            isbn = UUID.randomUUID().toString(),
            title = "Surrendered Single",
            author = "Laura Doyle",
            cover = "https://covers.openlibrary.org/b/id/471953-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "17",
            isbn = UUID.randomUUID().toString(),
            title = "The River Devil",
            author = "Diane Whiteside",
            cover = "https://covers.openlibrary.org/b/id/505653-M.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "18",
            isbn = UUID.randomUUID().toString(),
            title = "Koralina",
            author = "Neil Gaiman",
            cover = "https://covers.openlibrary.org/b/id/12634691-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "19",
            isbn = UUID.randomUUID().toString(),
            title = "Fifty Shades of Grey",
            author = "E. L. James",
            cover = "https://covers.openlibrary.org/b/id/10740111-L.jpg",
            price = 14.99,
            rating = 4
        ),
        Book(
            bookId = "20",
            isbn = UUID.randomUUID().toString(),
            title = "Court of Mist and Fury",
            author = "Sarah J. Maas",
            cover = "https://covers.openlibrary.org/b/id/14416194-L.jpg",
            price = 14.99,
            rating = 5
        ),
        Book(
            bookId = "21",
            isbn = UUID.randomUUID().toString(),
            title = "Fourth Wing",
            author = "Rebecca Yarros",
            cover = "https://covers.openlibrary.org/b/id/14407898-L.jpg",
            price = 14.99,
            rating = 5
        ),
    )

    val authors = listOf(Author("1", "title", "cover", "authorName", "Book"))
    
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