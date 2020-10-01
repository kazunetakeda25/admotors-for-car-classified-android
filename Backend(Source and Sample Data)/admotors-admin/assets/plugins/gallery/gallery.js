let FgGallery = function(selector) {
    isTrue = false;
    this.argumens = arguments;
    this.gallerySelector = document.querySelectorAll(selector);
    this.activeImage; // active (open) image
    this.bodyCover = document.createElement('div');
    
    if (!document.querySelector('.body-cover')) {
        document.body.appendChild(this.bodyCover);
        this.bodyCover.classList.add('body-cover');
        document.body.appendChild(this.bodyCover);
        var bodyCoverDeps = `<div>
            <div class="close-btn">
                <svg class="close-svg" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 21.9 21.9" xmlns:xlink="http://www.w3.org/1999/xlink">   <path class="close-svg" d="M14.1,11.3c-0.2-0.2-0.2-0.5,0-0.7l7.5-7.5c0.2-0.2,0.3-0.5,0.3-0.7s-0.1-0.5-0.3-0.7l-1.4-1.4C20,0.1,19.7,0,19.5,0  c-0.3,0-0.5,0.1-0.7,0.3l-7.5,7.5c-0.2,0.2-0.5,0.2-0.7,0L3.1,0.3C2.9,0.1,2.6,0,2.4,0S1.9,0.1,1.7,0.3L0.3,1.7C0.1,1.9,0,2.2,0,2.4  s0.1,0.5,0.3,0.7l7.5,7.5c0.2,0.2,0.2,0.5,0,0.7l-7.5,7.5C0.1,19,0,19.3,0,19.5s0.1,0.5,0.3,0.7l1.4,1.4c0.2,0.2,0.5,0.3,0.7,0.3  s0.5-0.1,0.7-0.3l7.5-7.5c0.2-0.2,0.5-0.2,0.7,0l7.5,7.5c0.2,0.2,0.5,0.3,0.7,0.3s0.5-0.1,0.7-0.3l1.4-1.4c0.2-0.2,0.3-0.5,0.3-0.7  s-0.1-0.5-0.3-0.7L14.1,11.3z"/> </svg>
            </div>
            <div class="gallery-arrow next-btn"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 492.004 492.004" xml:space="preserve"> <g> <g> <path d="M382.678,226.804L163.73,7.86C158.666,2.792,151.906,0,144.698,0s-13.968,2.792-19.032,7.86l-16.124,16.12 c-10.492,10.504-10.492,27.576,0,38.064L293.398,245.9l-184.06,184.06c-5.064,5.068-7.86,11.824-7.86,19.028 c0,7.212,2.796,13.968,7.86,19.04l16.124,16.116c5.068,5.068,11.824,7.86,19.032,7.86s13.968-2.792,19.032-7.86L382.678,265 c5.076-5.084,7.864-11.872,7.848-19.088C390.542,238.668,387.754,231.884,382.678,226.804z"/> </g> </g> </svg></div>
            <div class="gallery-arrow prev-btn"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 492 492" xml:space="preserve"> <g> <g> <path d="M198.608,246.104L382.664,62.04c5.068-5.056,7.856-11.816,7.856-19.024c0-7.212-2.788-13.968-7.856-19.032l-16.128-16.12 C361.476,2.792,354.712,0,347.504,0s-13.964,2.792-19.028,7.864L109.328,227.008c-5.084,5.08-7.868,11.868-7.848,19.084 c-0.02,7.248,2.76,14.028,7.848,19.112l218.944,218.932c5.064,5.072,11.82,7.864,19.032,7.864c7.208,0,13.964-2.792,19.032-7.864 l16.124-16.12c10.492-10.492,10.492-27.572,0-38.06L198.608,246.104z"/> </g> </g> </svg></div>
        </div>`;
        bodyCoverDeps = document.createRange().createContextualFragment(bodyCoverDeps).firstElementChild
        this.bodyCover.appendChild(bodyCoverDeps);
    }

    this.nextBtn = document.querySelector('.next-btn'); // next button
    this.prevBtn = document.querySelector('.prev-btn'); // previous button
    this.closeBtb = document.querySelector('.close-btn'); // close image button

    this.gallerySequence = 0; // gallery
    this.galleryItemSequence = 0; // gallery image

    // Adding cols class
    this.gallerySelector[0].classList.add(`cols-${this.argumens[1].cols}`);

    // Original images
    this.originalImages = {} // galleries with images

    // init functions
    this.generateItems();
    this.closeImage();
    this.styles();
}

// Generate image items
FgGallery.prototype.generateItems = function() {
    let This = this;
    this.gallerySelector.forEach(function(galleryContainer, i) {
        This.originalImages[`gallery${i}`] = []

        galleryContainer.querySelectorAll('img').forEach(function(imageItems, key) {
            This.originalImages[`gallery${i}`].push(imageItems); // Save original images

            // create new image elements
            var newImages = '';
            newImages += `<div class="gallery-items" style="background: url(${imageItems.src}) center / cover; "></div>`
            newImages = galleryContainer.appendChild(document.createRange().createContextualFragment(newImages).firstElementChild);
            imageItems.remove(); // Remove original images
            
            // open image
            newImages.onclick = function() {
                This.galleryItemSequence = key;
                This.gallerySequence = i;
                This.next();
                This.back();

                This.bodyCover.classList.add('active'); // active background cover
                This.activeImage = This.originalImages[`gallery${i}`][key].cloneNode(true);
                This.bodyCover.appendChild(This.activeImage);
                imagePopResize(This.activeImage);
            }

            // sizing image on window resize
            window.addEventListener('resize', function() {
				if (This.activeImage) {
                    imagePopResize(This.activeImage);
                }
			})
                
            // sizing image
            function imagePopResize(elem) {
				if (elem.naturalWidth < window.innerWidth && elem.naturalHeight < window.innerHeight ) {
					elem.style.width = 'auto';
					elem.style.height = 'auto';
				} 

				if (elem.offsetWidth > window.innerWidth) {
                    elem.style.width = '80%';
					elem.style.height = 'auto';
				}

				if (elem.offsetHeight > window.innerHeight) {
                    elem.style.width = 'auto';
					elem.style.height = '80%';
				}
			}
        })
    })
}

// next
FgGallery.prototype.next = function() {
    this.nextBtn.onclick = () => {
        this.galleryItemSequence++
        if (this.galleryItemSequence < this.originalImages[`gallery${this.gallerySequence}`].length) {
            this.activeImage.src = this.originalImages[`gallery${this.gallerySequence}`][this.galleryItemSequence].src;
        } else {
            this.galleryItemSequence = 0
            this.activeImage.src = this.originalImages[`gallery${this.gallerySequence}`][0].src;
        }
    }
}

// back
FgGallery.prototype.back = function() {
    this.prevBtn.onclick = () => {
        this.galleryItemSequence--;
        if (this.galleryItemSequence > -1) {
            this.activeImage.src = this.originalImages[`gallery${this.gallerySequence}`][this.galleryItemSequence].src
        } else {
            this.galleryItemSequence = this.originalImages[`gallery${this.gallerySequence}`].length - 1
            this.activeImage.src = this.originalImages[`gallery${this.gallerySequence}`][this.galleryItemSequence].src
        }
    }
}

// close image
FgGallery.prototype.closeImage = function() {
    this.bodyCover.onclick = (e) => {
    if (e.target.classList.contains('active') || e.target.classList.contains('close-btn') || e.target.classList.contains('close-svg')) {
            this.bodyCover.classList.remove('active');
            this.bodyCover.querySelector('img').remove();
        }
    }
}

// applying styles
FgGallery.prototype.styles = function() {
    if (typeof this.argumens[1].style) {
        for (const key in this.argumens[1].style) {
            document.querySelectorAll(`${this.argumens[0]} .gallery-items`).forEach(items => {
                items.style[key] = this.argumens[1].style[key]
            })
        }        
    }
}