package tungp.android.bazarbooks.data.local.database

import tungp.android.bazarbooks.data.local.entity.AuthorEntity
import tungp.android.bazarbooks.data.local.entity.BookEntity
import tungp.android.bazarbooks.data.local.entity.CategoryEntity
import java.util.Date
import java.util.UUID

// --- Sample Authors ---
val author1Id = UUID.randomUUID().toString()
val author1 = AuthorEntity(
    authorId = author1Id,
    name = "J.K. Rowling",
    biography = "British author, best known for the Harry Potter series.",
    birthDate = Date(),
    nationality = "British",
)

val author2Id = UUID.randomUUID().toString()
val author2 = AuthorEntity(
    authorId = author2Id,
    name = "George Orwell",
    biography = "English novelist, essayist, journalist and critic.",
    birthDate = Date(),
    nationality = "British",
)

// --- Sample Categories ---
val category1Id = UUID.randomUUID().toString()
val category1 = CategoryEntity(
    categoryId = category1Id,
    name = "Fantasy",
    description = "Fiction in an unreal setting that often includes magic, magical creatures, or the supernatural.",
    sortOrder = 0,
)

val category2Id = UUID.randomUUID().toString()
val category2 = CategoryEntity(
    categoryId = category2Id,
    name = "Dystopian",
    description = "Fiction that explores social and political structures in a dark, nightmare world.",
    sortOrder = 0,
)

// --- Sample Books ---
val book1 = BookEntity(
    bookId = UUID.randomUUID().toString(),
    title = "Harry Potter and the Philosopher's Stone",
    authorId = author1Id,
    categoryId = category1Id,
    isbn = "978-0747532699",
    description = "The first novel in the Harry Potter series.",
    coverImageUrl = "https://example.com/hp_stone.jpg",
    price = 10.99,
    publishedDate = createDate(1997, 5, 26),
    publisher = "Bloomsbury",
    stockQuantity = 50,
    averageRating = 4.5f,
    createdAt = Date(),
    updatedAt = Date()
)

val book2 = BookEntity(
    bookId = UUID.randomUUID().toString(),
    title = "Nineteen Eighty-Four",
    authorId = author2Id,
    categoryId = category2Id,
    isbn = "978-0451524935",
    description = "A dystopian social science fiction novel and cautionary tale.",
    coverImageUrl = "https://example.com/1984.jpg",
    price = 8.50,
    discountPrice = 7.99,
    publishedDate = createDate(1949, 5, 8),
    publisher = "Sicker & Wartburg",
    stockQuantity = 30,
    averageRating = 4.7f,
    createdAt = Date(),
    updatedAt = Date()
)

val book3 = BookEntity(
    bookId = UUID.randomUUID().toString(),
    title = "Animal Farm",
    authorId = author2Id,
    categoryId = category2Id, // Can also be null if a book doesn't have a category initially
    isbn = "978-0451526342",
    description = "A satirical allegorical novella.",
    price = 7.00,
    publishedDate = createDate(1945, 7, 17),
    publisher = "Sicker & Wartburg",
    stockQuantity = 20,
    createdAt = Date(),
    updatedAt = Date()
)