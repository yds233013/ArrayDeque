# ArrayDeque – Circular Array Double-Ended Queue

🧠 **Project:** Array-Backed Deque Data Structure  
🧰 **Language:** Java  
🎯 **Focus:** Dynamic Arrays, Circular Indexing, Constant-Time Operations, Test-Driven Development

---

## 📌 Overview

This project implements a **resizable, circular array-based double-ended queue (deque)** from scratch using Java. The `ArrayDeque` supports **constant-time additions and removals from both ends**, dynamic resizing, and memory-efficient operations — all without using Java's built-in collections.

Unlike linked list implementations, this version uses **a raw array as the underlying storage** and maintains front/back indices with wrap-around behavior to simulate a circular buffer.

---

## 🚀 Features Implemented

| Method | Description |
|--------|-------------|
| `addFirst(T item)` | Adds item to the front in O(1) time |
| `addLast(T item)` | Adds item to the back in O(1) time |
| `removeFirst()` | Removes and returns the front item |
| `removeLast()` | Removes and returns the last item |
| `get(int index)` | Retrieves an item by index in O(1) |
| `getRecursive(int index)` | Throws exception (unsupported for arrays) |
| `isEmpty()` | Returns true if the deque is empty |
| `size()` | Returns the current number of items |
| `toList()` | Returns a `List<T>` view of the deque |

---

## ⚙️ Internal Design

- **Circular Array**:
  - Uses modulo arithmetic to wrap around the front/back pointers.
  - Avoids shifting elements when adding/removing.
- **Dynamic Resizing**:
  - Automatically resizes when full (doubling size).
  - Shrinks down when usage < 25% (if size ≥ 16).
- **Constant-Time Operations**:
  - All add/remove/get operations are O(1) (except during resizing).

---

## 💡 Key Concepts

- Manual memory and array management
- Circular indexing and pointer arithmetic
- Efficient resizing while preserving order
- Avoiding memory leaks by nulling unused slots
- Using tests to guide implementation (test-driven development)

---

## 🧪 Testing

A comprehensive test suite was developed using **Google Truth** assertions. It includes:

- Edge case testing (e.g., removing from an empty deque)
- Randomized integration tests (multiple operations in sequence)
- Resizing stress tests (10,000+ insertions/removals)
- Tests for all supported methods, including negative cases

📁 Project Structure

ArrayDeque/
├── src/
│   ├── ArrayDeque.java       👈 Core deque logic
│   └── Deque.java            👈 Interface
├── tests/
│   └── ArrayDequeTest.java   👈 Test suite (Truth-based)
└── README.md


🧭 Example Usage

Deque<String> deque = new ArrayDeque<>();
deque.addFirst("Hello");
deque.addLast("World");
System.out.println(deque.get(1)); // Output: World
deque.removeFirst(); 
